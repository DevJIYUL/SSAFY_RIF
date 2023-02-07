import RPi.GPIO as GPIO
from picamera2 import Picamera2, Preview
import time
import detect
from types import SimpleNamespace

# 사진 3장 찍고 yolo 돌리고 결과 출력하는 예제 생성
# plastic_pic.jpg, recycle_pic.jpg, trash_pic.jpg

servo = 2

cam = Picamera2()
cam_config = cam.create_still_configuration(main={"size":(1920, 1080)}, lores={"size":(640, 480)}, display="main")

cam.configure(cam_config)
#cam.start_preview(Preview.QTGL)
cam.start()

GPIO.setmode(GPIO.BCM)
GPIO.setup(servo, GPIO.OUT)

pwm = GPIO.PWM(servo, 50)
pwm.start(3.0)
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


pwm.stop()
GPIO.cleanup()


# yolo
parser = SimpleNamespace()

# folder 사진 전체 확인
parser.source = '/home/parkdoyun/pi_img' # image file path
parser.weights = 'best.pt'
parser.conf_thres = 0.25
#parser.save_txt = True
parser.nosave = True # save X

print(parser)

item_list = detect.main(parser)
print(item_list)


