from PySide2.QtWidgets import *
from PySide2.QtCore import *
import sys
from testSwitch import Ui_Form
from gpiozero import Button, DistanceSensor
from time import sleep
import threading

# 클릭하면 Qt 내 이미지 전환됨

class MyApp(QWidget, Ui_Form):
    #sensor = DistanceSensor(17, 27)
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.showFullScreen()
        self.main()
       
       # button event
        self.btn1 = Button(2)
        self.btn2 = Button(3)
        self.btn1.when_pressed = self.pressed1
        self.btn2.when_pressed = self.pressed2

        # distance sensor event
        self.th = MyThread()
        self.th.mySignal.connect(self.senseCustomer)
        self.th.start()

    def main(self):
        pass 
        
    def senseCustomer(self, val):
        print(val)
        if val == 1:
            self.customer()

class MyThread(QThread):

    mySignal = Signal(int)

    def __init__(self):
        super().__init__()
        self.sensor = DistanceSensor(17, 27)
        print("dist start")

    def run(self):
        while True:
            sleep(0.1)
            print(self.sensor.distance)
            if self.sensor.distance < 0.3:
                sleep(0.5)
                if self.sensor.distance < 0.3:
                    self.mySignal.emit(1)
                else:
                    self.mySignal.emit(0)


app = QApplication()
win = MyApp()

win.show()
app.exec_()
