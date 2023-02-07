from PySide2.QtWidgets import *
import sys
from testSwitch import Ui_Form
from gpiozero import Button


# 클릭하면 Qt 내 이미지 전환됨

class MyApp(QWidget, Ui_Form):
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

    def main(self):
        pass

app = QApplication()
win = MyApp()

win.show()
app.exec_()
