import RPi.GPIO as GPIO
from picamera2 import Picamera2
import time
import detect
import socket
from types import SimpleNamespace
import json

# socket - server
# QT에서 신호 받으면 사진 3장 찍어서 결과 딕셔너리 json 파일로 저장
# 클라이언트로부터 'exit' 받으면 종료
# error -> -1 client 전송
# ok -> 0 client 전송

HOST = '127.0.0.1'
PORT = 9999

server_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

server_soc.bind((HOST, PORT))

server_soc.listen()

#client_soc, addr = server_soc.accept()

#print('Connected by', addr)

servo = 2

cam = Picamera2()
cam_config = cam.create_still_configuration(main={"size":(720, 1080)}, lores={"size":(640, 480)}, display="main")

cam.configure(cam_config)
cam.start()

GPIO.setmode(GPIO.BCM)
GPIO.setup(servo, GPIO.OUT)

pwm = GPIO.PWM(servo, 50)

while True:

    client_soc, addr = server_soc.accept()
    print('Connected by', addr)
    data = client_soc.recv(1024)

    # 다음 신호 올때까지 block 해야 함
    if not data:
        # 다음 신호 올 때까지 기다림
        print("[QUIT] CLIENT QUIT")
        continue
    elif data.decode() == 'exit':
        break

    # data 받으면yolo start
    print('Received from', addr, data.decode())
    
    try:
        pwm.start(3.5)
        time.sleep(0.5)
        cam.capture_file("/home/parkdoyun/pi_img/plastic_pic.jpg")
        time.sleep(0.2)

        pwm.ChangeDutyCycle(5.25)
        time.sleep(0.5)
        cam.capture_file("/home/parkdoyun/pi_img/recycle_pic.jpg")
        time.sleep(0.2)

        pwm.ChangeDutyCycle(7)
        time.sleep(0.5)
        cam.capture_file("/home/parkdoyun/pi_img/trash_pic.jpg")
        time.sleep(0.2)

        pwm.ChangeDutyCycle(5.25)
        time.sleep(0.5)

        parser = SimpleNamespace()

        parser.source = '/home/parkdoyun/pi_img'
        parser.weights = 'pts/best1.pt'
        parser.conf_thres = 0.25
        parser.nosave = True

        print(parser)

        item_list = detect.main(parser)
        #print(item_list)

        with open('/home/parkdoyun/yoloresult/result.json', 'w', encoding='utf-8') as file:
            json.dump(item_list, file)

        client_soc.sendall('0'.encode()) # no error
        client_soc.close()

    except: # error
        client_soc.sendall('-1'.encode()) # client로 전송
        client_soc.close()
        break

pwm.stop()
GPIO.cleanup()

server_soc.close()

