from PySide2.QtWidgets import *
from PySide2.QtCore import *
import RPi.GPIO as GPIO
from mfrc522 import SimpleMFRC522
import sys
from qtScreen import Ui_Form
import threading
import requests
import json
import time
from gpiozero import DistanceSensor
import socket

# 초음파로 화면 전환
# 클릭하면 Qt 내 이미지 전환됨
# thread로 RFID 태그 -> 화면 전환되도록
# thread로 순서 지정
# thread로 guide1, guide2 toggle
# thread로 socket (yolo, cam) server와 통신
# 수거하면서 data server로 전송

# item(eng)
item_eng = {'clear plastic cup lid': 0, 'clear plastic cup' : 1, 'colored plastic cup lid' : 2,
        'inked plastic cup' : 3, 'litter' : 4, 'paper cup holder' : 5,
        'paper cup with cup holder' : 6, 'paper cup' : 7,
        'plastic bottle cap' : 8, 'plastic bottle' : 9,
        'plastic cup with cup holder' : 10,
        'straw' : 11, 'white plastic cup lid' : 12,
        'label' : 13, 'plastic bottle labeled' : 14}

# item(kor)
item_kor = ['투명 플라스틱 뚜껑', '투명 플라스틱 컵', '유색 플라스틱 뚜껑', 
        '인쇄된 플라스틱 컵', '라벨지', '종이 컵홀더', '종이컵 및 홀더',
        '종이컵', '페트병 뚜껑', '페트병', '플라스틱컵 및 홀더',
        '빨대', '흰색 플라스틱 뚜껑', '라벨지', '라벨 있는 페트병']


# plastic : P, recycle : R, trash : T, 재분류 : X
trash_label = ['R', 'R', 'P', 'P', 'T', 'R', 'X', 'T', 'P', 'R', 'X', 'T', 'P', 'P', 'X']

sensor_num = 1 # 1 : RFID, 2 : distance sensor
toggle_flag = 0 # 1 : toggle (guide 1, 2)
wrongFlag = 0
socketFlag = 0
show_wrong_cnt = 0

# 전송할 수거 데이터
plasticTotal = 0
plasticOk = 0
recycleTotal = 0
recycleOk = 0

# user uid
user_uid = 'xxxxxxxx'

wrong_list = ["", "", ""]

class MyApp(QWidget, Ui_Form):
     
    servo_pwm = 0
    def __init__(self):
        global sensor_num

        super().__init__()
        self.setupUi(self)
        self.showFullScreen()

        sensor_num = 1

        # buzzer
        self.buzzer = 21
        
        GPIO.setmode(GPIO.BCM)
        
        # buzzer
        GPIO.setup(self.buzzer, GPIO.OUT)

        self.pwm = GPIO.PWM(self.buzzer, 1.0)
        self.pwm.start(50.0)

        self.scale = [262, 294, 330]

        # make RFID thread
        self.rfid_th = MyThread()
        self.rfid_th.distanceSignal.connect(self.senseDisplay)
        self.rfid_th.mySignal.connect(self.switchDisplay)
        self.rfid_th.start()

        # toggle thread
        self.toggle_th = toggleThread()
        self.toggle_th.mySignal.connect(self.toggleDisplay)
        self.toggle_th.start()

        # socket Thread
        self.socketThread = socketThread()
        self.socketThread.socketSignal.connect(self.detection)
        self.socketThread.start()

        # button
        self.button1 = 3
        self.button2 = 4

        GPIO.setup(self.button1, GPIO.IN, pull_up_down=GPIO.PUD_UP)
        GPIO.setup(self.button2, GPIO.IN, pull_up_down=GPIO.PUD_UP)

        GPIO.add_event_detect(self.button1, GPIO.BOTH, callback=self.btn1Pressed, bouncetime=500)
        GPIO.add_event_detect(self.button2, GPIO.BOTH, callback=self.btn2Pressed, bouncetime=500)
        
        # servo trashcan
        self.servo = 22
        GPIO.setup(self.servo, GPIO.OUT)
        self.servo_pwm = GPIO.PWM(self.servo, 50)
        self.servo_pwm.start(3.0)
        self.main()

        # qt timer showWrong
        self.tm_showWrong = QTimer()
        self.tm_showWrong.setInterval(2000)
        self.tm_showWrong.timeout.connect(self.showWrong)
        
        # qt timer askAgain
        self.tm_askAgain = QTimer()
        self.tm_askAgain.setInterval(50)
        self.tm_askAgain.timeout.connect(self.askAgain)
        
        # qt timer collect
        self.tm_collect = QTimer()
        self.tm_collect.setInterval(50)
        self.tm_collect.timeout.connect(self.collectGarbage)
        
        # qt timer showPoint
        self.tm_showPoints = QTimer()
        self.tm_showPoints.setInterval(50)
        self.tm_showPoints.timeout.connect(self.showPoints)

        #qt timer showFirst
        self.tm_showFirst = QTimer()
        self.tm_showFirst.setInterval(2000)
        self.tm_showFirst.timeout.connect(self.showFirst)

    def main(self):
        pass

    def socketDisplay(self, val):
        self.setupImageNum(val)

    def closeEvent(self, e):
        print("CLOSE")
        self.hide()
        self.toggle_th.stop()
        self.rfid_th.stop()
        self.socketThread.stop()

    def senseDisplay(self, val):
        print("senseDisplay()")

        if val == -1:
            # change display
            self.sense_display()

    def btn1Pressed(self, val):
        global toggle_flag, socketFlag

        print("BTN1")
        if self.arr_idx == 3 or self.arr_idx == 4:
            if GPIO.input(3):
                pass
            else:
                toggle_flag = 0    
                self.setupImageNum(5)
                socketFlag = 1
        elif self.arr_idx == 8:
            if GPIO.input(3):
                pass
            else:
                toggle_flag = 1

    def btn2Pressed(self, val):
        print("BTN2")
        if self.arr_idx == 8:
            if GPIO.input(4):
                pass
            else:
                self.setupImageNum(9)
                time.sleep(2)
                self.collectGarbage()
    
    def switchDisplay(self, val):
        global toggle_flag, user_uid 

        print("RFID val : " + hex(val))
        
        # switch display
        if val: # val is not null
            uid_str = str(hex(val))
            uid_str = uid_str[2:]
            user_uid = uid_str

        
            r = requests.get('https://i8a501.p.ssafy.io/api/member', params={'uid': uid_str}, verify=False)
            
            if r.status_code != 200:
                print("Connect ERROR")
            else:
                for i in range(3):
                    self.pwm.ChangeFrequency(self.scale[i])
                    time.sleep(0.2)
                self.pwm.stop()

                print(r.text)
                json_info = r.json()
                self.switch_display(json_info['name'])
                time.sleep(2)
                toggle_flag = 1
                

    def toggleDisplay(self, val):
        if val == 1:
            self.guideToggle()

    # 분류하는 함수 / 후에 yolo detect 함수 들어가면 됨
    def detection(self, val):
        global user_uid, wrongFlag, sensor_num, socketFlag
        
        # logout
        if val == 0:
            print("empty logout")
            self.setupImageNum(0)
            user_uid = ''
            sensor_num = 1
            wrongFlag = 0
            return
        # wrong
        elif val == 1 or val == 3:
            # X 있을 때
            if val == 3:
                print("X YES")
            # 한 번도 안틀렸으면
            if wrongFlag == 0:
                print("Wrong first time")
                wrongFlag = 1
                # 버릴지 다시 할지 선택하기
            
                self.setupImageNum(7)
                self.tm_showWrong.start()
                #time.sleep(2)
                #self.showWrong()
                #self.setupImageNum(8)
            
            # 두 번 틀렸으면 수거
            elif wrongFlag == 1:
                wrongFlag = 2
                print("Wrong second time")
                self.setupImageNum(7)
                self.tm_showWrong.start()
        # Right
        elif val == 2:
            print("Right")
            self.setupImageNum(6)
            time.sleep(2)
            self.tm_collect.start()
    
    # 틀린 항목 보여주는 함수 
    def showWrong(self):
        global wrong_list, show_wrong_cnt, wrongFlag

        if self.tm_showWrong.isActive() == True and show_wrong_cnt == 3:
            self.tm_showWrong.stop()
            if wrongFlag == 1:
                self.tm_askAgain.start()
            elif wrongFlag == 2:
                self.tm_collect.start()
            self.wrong_list_text.setText("")
            show_wrong_cnt = 0
            return

        self.setupImageNum(11 + show_wrong_cnt)
        self.wrong_list_text.setText(wrong_list[show_wrong_cnt])
        print(wrong_list[show_wrong_cnt])
        show_wrong_cnt += 1
        
        
    def askAgain(self):
        if self.tm_askAgain.isActive() == True:
            self.tm_askAgain.stop()
        self.setupImageNum(8)

    # 쓰레기통 뚜껑 돌리는 코드
    def collectGarbage(self):
        if self.tm_collect.isActive() == True:
            self.tm_collect.stop()
            self.setupImageNum(9)
            self.servo_pwm.ChangeDutyCycle(12)
            time.sleep(1)
            self.servo_pwm.ChangeDutyCycle(3)
            time.sleep(0.5)
            self.tm_showPoints.start()
            return

        self.setupImageNum(9)
        self.servo_pwm.ChangeDutyCycle(12)
        time.sleep(1)
        self.servo_pwm.ChangeDutyCycle(3)
        time.sleep(0.5)
        self.showPoints()

    # 쓰레기 수거 후 로그아웃 화면
    def showPoints(self):
        global user_uid, sensor_num, wrongFlag, plasticTotal, plasticOk, recycleTotal, recycleOk
        
        yoontae_flag = 0

        if self.tm_showPoints.isActive() == True:
            self.tm_showPoints.stop()
            yoontae_flag = 1

        # 수거 정보 데이터 전송
        #print("UID " + user_uid + ' ' + str(plasticTotal) + ' ' + str(plasticOk) + ' ' + str(recycleTotal) + ' ' + str(recycleOk)) 
        #requests.packages.urllib3.disable_warnings(category=InsecureRequestWarning)
        #urllib3.disable_warnings()
        headers = {'Content-Type': "application/json; charset=utf-8"}
        datas = {'uid' : str(user_uid),
            'plasticTotal' : str(plasticTotal), 'plasticOk' : str(plasticOk),
            'recycleTotal' : str(recycleTotal), 'recycleOk' : str(recycleOk)}
        print("===================server send data=======================") 
        print(headers)
        print(datas)

        res = requests.post('http://i8a501.p.ssafy.io:8080/api/member/riflog', headers=headers, json=datas, verify=False)
    
        print(res.json())

        if res.status_code != 200:
            print("[ERROR " + str(res.status_code) + "] CONNECT ERROR")
        else:
            recycle_json = res.json()
            print("NAME : ", recycle_json['name'])
            print("POINT : " , recycle_json['point'])
            print("EXP : ", recycle_json['exp'])


        if yoontae_flag == 1:
            self.setupImageNum(10)
            self.soogoe_name.setText(recycle_json['name'])
            self.soogoe_score.setText(str(recycle_json['point']))
            #time.sleep(3)
            #self.soogoe_name.setText("")
            #self.soogoe_score.setText("")
            self.tm_showFirst.start()
            return
        
        # 점수 데이터 보여주기
        self.setupImageNum(10)
        self.soogoe_name.setText(recycle_json['name'])
        self.soogoe_score.setText(str(recycle_json['point']))
        time.sleep(3)
        self.soogoe_name.setText("")
        self.soogoe_score.setText("")
        
        # logout
        user_uid = ''
        sensor_num = 1
        wrongFlag = 0

        if yoontae_flag == 1:
            self.tm_showFirst.start()
            return
        self.setupImageNum(0)
    
    def showFirst(self):
        global user_uid, sensor_num, wrongFlag

        if self.tm_showFirst.isActive() == True:
            self.tm_showFirst.stop()
        #self.soogoe_name.setText("")
        #self.soogoe_score.setText("")
        self.soogoe_name.setText("")
        self.soogoe_score.setText("")
        
        user_uid = ''
        sensor_num = 1
        wrongFlag = 0

        self.setupImageNum(0)


# RFID, distance sensor thread
class MyThread(QThread):

    distanceSignal = Signal(int)
    mySignal = Signal(int)
    buttonSignal = Signal(int)
    def __init__(self):
        super().__init__()
        # RFID sensor
        self.rfid_sensor = SimpleMFRC522()

        # distance sensor
        self.sensor = DistanceSensor(27, 17)
        
    def run(self):
        global sensor_num
        while True:
            if sensor_num == 1: # distance sensor
                print(self.sensor.distance)
                time.sleep(0.5)
                if self.sensor.distance < 0.15:
                    time.sleep(0.5)
                    if self.sensor.distance < 0.15:
                        self.distanceSignal.emit(-1)
                        sensor_num = 2
                
            elif sensor_num == 2:
                # RFID recognition
                self.id, self.text = self.rfid_sensor.read()
                print(hex(self.id))
                self.mySignal.emit(int(self.id))
                sensor_num = 3

    def stop(self):
        self.working = False
        self.quit()
        self.wait(1000)

# guide display thread
class toggleThread(QThread):
    mySignal = Signal(int)

    def __init__(self):
        super().__init__()
    
    def run(self):
        global toggle_flag
        while True:
            time.sleep(2)
            print("toggle_flag : ", toggle_flag)
            if toggle_flag == 1:
                self.mySignal.emit(1)
            

    def stop(self):
        self.working = False
        self.quit()
        self.wait(1000)

class socketThread(QThread):
    socketSignal = Signal(int)

    def __init__(self):
        super().__init__()
        self.HOST = '127.0.0.1'
        self.PORT = 9999

    def run(self):
        global socketFlag, item_eng, item_kor, trash_label, plasticTotal, plasticOk, recycleTotal, recycleOk, wrong_list

        while True:
            while socketFlag == 0:
                time.sleep(1)
                pass
            
            # client socket
            self.client_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.client_soc.connect((self.HOST, self.PORT))
            self.client_soc.sendall('yolo start'.encode())

            data = self.client_soc.recv(1024)

            if data.decode() == '-1': # yolo error
                print("[ERROR] YOLO SERVER ERROR")
                self.socketSignal.emit(5)
                time.sleep(2)
                break

            print("[OK] YOLO OK")

            with open('/home/parkdoyun/yoloresult/result.json', 'r') as file:
                item_list = json.load(file)
                print(item_list)

            # 적합인지 부적합인지
            x_yes = False # label 'X' 있는지
            collection_flag = True # 적합 : True, 부적합 : False
            item_empty = True # item_list is empty

            plasticTotal = 0
            plasticOk = 0
            recycleTotal = 0
            recycleOk = 0

            for j in range(3):
                print("\n=======" + str(j + 1) + "=========")
                wrong_list[j] = ""
                if len(item_list[j]) != 0:
                    item_empty = False

                for key, val in (item_list[j].items()):
                    label = trash_label[item_eng[key]]
                    if j == 0: # plastic
                        plasticTotal += val
                    elif j == 1:
                        recycleTotal += val

                    if label == 'X':
                        # 틀린 것만 출력
                        print(item_kor[item_eng[key]] + " " + str(val) + " => " + label)
                        wrong_list[j] += item_kor[item_eng[key]] + "\n"
                        x_yes = True
                    elif (label == 'P' and  j != 0) or (label == 'R' and j != 1) or (label == 'T' and j != 2):
                        # 틀린 것만 출력
                        print(item_kor[item_eng[key]] + " " + str(val) + " => " + label)
                        wrong_list[j] += item_kor[item_eng[key]] + "\n"
                        collection_flag = False
                    else: # right
                        if j == 0:
                            plasticOk += val
                        elif j == 1:
                            recycleOk += val


            # x 있을 때 
            if x_yes == True:
                print("X YES")
                collection_flag = False
                self.socketSignal.emit(3)

            elif item_empty == True:
                print("empty item => logout")
                # 로그아웃
                self.socketSignal.emit(0)

            elif collection_flag == False:
                print("WRONG")
                # 부적합
                self.socketSignal.emit(1)
                
            else:
                print("RIGHT")
                # 적합 -> 수거
                self.socketSignal.emit(2)

            self.client_soc.close()

            socketFlag = 0

    def stop(self):
        self.working = False
        self.quit()
        self.wait(1000)

app = QApplication()
win = MyApp()

win.show()
app.exec_()

