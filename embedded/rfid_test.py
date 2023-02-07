# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'text_change.ui'
##
## Created by: Qt User Interface Compiler version 6.4.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

# 대기화면에서 초음파로 넘어감
# 배경화면 라벨과 폰트 맞춤 1152 x 875
# RFID 태그하면 서버로부터 정보 받는 화면

# 1. RFID 태그 전self.name_text 내부 텍스트와 배경 없어야 함
# 2. RFID 태그 까지 thread로 대기

from PySide2.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide2.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide2.QtWidgets import (QApplication, QLabel, QSizePolicy, QWidget)
from PySide2.QtCore import QSize, Qt
import time

class Ui_Form(object):
    pix_arr = [] # picture
    arr_idx = 0

    def setupUi(self, Form):
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/daegi.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/start.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/name.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/guide1.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/guide2.png'))

        pixmap1 = self.pix_arr[self.arr_idx]
        pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)

        if not Form.objectName():
            Form.setObjectName(u"Form")
        Form.resize(1152, 875)

        # 사진 넣을 라벨
        self.label = QLabel(Form)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(0, 0, 1152, 875))
        self.label.setPixmap(pixmap)

        self.name_text = QLabel(Form)
        self.name_text.setObjectName(u"label")
        self.name_text.setGeometry(QRect(440, 320, 181, 101))

        font = QFont()
        font.setFamilies([u"\ub098\ub214\uc2a4\ud018\uc5b4_ac Bold"])
        font.setPointSize(36)
        font.setBold(True)
        font.setItalic(False)
        font.setUnderline(False)
        font.setStrikeOut(False)

        self.name_text.setFont(font)
        self.name_text.setLayoutDirection(Qt.LeftToRight)
        # self.name_text.setStyleSheet(u"background-color: rgb(234, 231, 177);\n"
        self.name_text.setStyleSheet(
"color: rgb(89, 158, 49);\n"
"font: 450 49pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")

        self.retranslateUi(Form)

        QMetaObject.connectSlotsByName(Form)
    # setupUi

    def retranslateUi(self, Form):
        Form.setWindowTitle(QCoreApplication.translate("Form", u"Form", None))
        self.name_text.setText(QCoreApplication.translate("Form", u"", None)) # set font text
    # retranslateUi

    # rfid switch display
    def switch_display(self, name):
        if self.arr_idx == 1:
             print("switch_display() => name : ", name)
             self.arr_idx = 2
             #pixmap1 = self.pix_arr[self.arr_idx]
             #pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
             #self.label.setPixmap(pixmap)
             self.setupImage()
             # name_text visible
             
             self.name_text.setText(name) # set Name
             self.name_text.setStyleSheet(u"background-color: rgb(234, 231, 177);\n"
             "color: rgb(89, 158, 49);\n"
             "font: 450 49pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")
            
             #time.sleep(3)
             
             self.arr_idx = 3
       
    def setupImage(self):
            pixmap1 = self.pix_arr[self.arr_idx]
            pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
            self.label.setPixmap(pixmap)


    # distance sensor switch display
    def sense_display(self):
        if self.arr_idx == 0:
            print("sense_display()")
            self.arr_idx = 1
            
            pixmap1 = self.pix_arr[self.arr_idx]
            pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
            self.label.setPixmap(pixmap)


    def guideToggle(self):
        
        if self.arr_idx == 3:
            self.arr_idx = 4
        else:
            self.arr_idx = 3

        #pixmap1 = self.pix_arr[self.arr_idx]
        #pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
        #self.label.setPixmap(pixmap)

        self.setupImage()

        # name_text invisible
        self.name_text.setText(QCoreApplication.translate("Form", "", None)) # set Name
        self.name_text.setStyleSheet("color: rgb(89, 158, 49);\n"
        "font: 450 49pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")
    
