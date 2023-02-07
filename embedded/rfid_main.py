from PySide2.QtWidgets import *
from PySide2.QtCore import *
import RPi.GPIO as GPIO
from mfrc522 import SimpleMFRC522
import sys
#from test import Ui_Form
from rfid_test import Ui_Form
import threading
import requests
import json
import time
from gpiozero import DistanceSensor

# 초음파로 화면 전환
# 클릭하면 Qt 내 이미지 전환됨
# thread로 RFID 태그 -> 화면 전환되도록
# thread로 순서 지정
# thread로 guide1, guide2 toggle

sensor_num = 1 # 1 : RFID, 2 : distance sensor
toggle_flag = 0 # 1 : toggle (guide 1, 2)

class MyApp(QWidget, Ui_Form):
    #global sensor_num 

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

        # RFID sensor
        #self.rfid_sensor = SimpleMFRC522()

        # make distance sensor thread

        # make RFID thread
        self.rfid_th = MyThread()
        self.rfid_th.distanceSignal.connect(self.senseDisplay)
        self.rfid_th.mySignal.connect(self.switchDisplay)
        self.rfid_th.start()

        # toggle thread
        self.toggle_th = toggleThread()
        self.toggle_th.mySignal.connect(self.toggleDisplay)
        self.toggle_th.start()

        self.main()

    def main(self):
        pass

    def closeEvent(self, e):
        print("CLOSE")
        self.hide()
        #GPIO.cleanup()
        self.toggle_th.stop()
        self.rfid_th.stop()

    def senseDisplay(self, val):
        print("senseDisplay()")

        if val == -1:
            # change display
            self.sense_display()
        

    def switchDisplay(self, val):
        global toggle_flag 

        print("RFID val : " + hex(val))
        
        # switch display
        if val: # val is not null
            uid_str = str(hex(val))
            uid_str = uid_str[2:]

        
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

# RFID, distance sensor thread
class MyThread(QThread):
    #global sensor_num

    distanceSignal = Signal(int)
    mySignal = Signal(int)

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
        #GPIO.clenup()
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

app = QApplication()
win = MyApp()

win.show()
app.exec_()

