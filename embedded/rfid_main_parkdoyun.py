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

# 클릭하면 Qt 내 이미지 전환됨
# thread로 RFID 태그 -> 화면 전환되도록

class MyApp(QWidget, Ui_Form):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.showFullScreen()
        self.main()

        self.buzzer = 21
        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.buzzer, GPIO.OUT)

        self.pwm = GPIO.PWM(self.buzzer, 1.0)
        self.pwm.start(50.0)

        self.scale = [262, 294, 330]

        # RFID sensor
        self.rfid_sensor = SimpleMFRC522()

        # make RFID thread
        self.rfid_th = MyThread()
        self.rfid_th.mySignal.connect(self.switchDisplay)
        self.rfid_th.start()

    def main(self):
        pass

    def switchDisplay(self, val):
        print("RFID val : " + hex(val))
        
        # switch display
        if val: # val is not null
            uid_str = str(hex(val))
            uid_str = uid_str[2:]

        
            r = requests.get('http://i8a501.p.ssafy.io:8080/member', params={'uid': uid_str})
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
            


class MyThread(QThread):

    mySignal = Signal(int)


    def __init__(self):
        super().__init__()
        # RFID sensor
        self.rfid_sensor = SimpleMFRC522()
    

    def run(self):
        # RFID recognition
        self.id, self.text = self.rfid_sensor.read()
        self.mySignal.emit(self.id)
        

app = QApplication()
win = MyApp()

win.show()
app.exec_()

