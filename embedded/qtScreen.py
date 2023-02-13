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
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/taking_picture.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/OK.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/not_OK.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/jaeboonryu.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/soogoe.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/finish.png'))
        
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/wrong_specific_plastic.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/wrong_specific_recycle.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/wrong_specific_trash.png'))

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

        # 처음 이름 표시
        self.name_text = QLabel(Form)
        self.name_text.setObjectName(u"label")
        self.name_text.setGeometry(QRect(440, 320, 181, 101))
        self.name_text.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.name_text.setLayoutDirection(Qt.LeftToRight)
        self.name_text.setStyleSheet(
"color: rgb(89, 158, 49);\n"
"font: 450 49pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")


        # 나중에 수거 이름 표시
        self.soogoe_name = QLabel(Form)
        self.soogoe_name.setObjectName(u"soogoe_name")
        self.soogoe_name.setGeometry(QRect(130, 530, 181, 101))
        self.soogoe_name.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.soogoe_name.setStyleSheet(u"color: rgb(89, 158, 49);\n"
"font: 450 45pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")

        # 수거 점수
        self.soogoe_score = QLabel(Form)
        self.soogoe_score.setObjectName(u"soogoe_score")
        self.soogoe_score.setGeometry(QRect(430, 540, 71, 101))
        self.soogoe_score.setAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.soogoe_score.setStyleSheet(u"color: rgb(89, 158, 49);\n"
"font: 450 43pt \"\ub098\ub214\uc2a4\ud018\uc5b4\";")
        
        # 잘못된 애들 표시 라벨
        self.wrong_list_text = QLabel(Form)
        self.wrong_list_text.setObjectName(u"wrong_list_text")
        self.wrong_list_text.setGeometry(QRect(220, 410, 711, 281))
        self.wrong_list_text.setStyleSheet(u"font : 45pt \"\ub098\ub214\uc2a4\ud018\uc5b4 \ub124\uc624 Regular\";\n"
"color: rgb(189, 0, 0);")
        self.wrong_list_text.setTextFormat(Qt.AutoText)
        self.wrong_list_text.setAlignment(Qt.AlignCenter)

        self.retranslateUi(Form)

        QMetaObject.connectSlotsByName(Form)
    # setupUi

    def retranslateUi(self, Form):
        Form.setWindowTitle(QCoreApplication.translate("Form", u"Form", None))
        self.name_text.setText(QCoreApplication.translate("Form", u"", None)) # set font text
        self.soogoe_name.setText(QCoreApplication.translate("Form", u"", None)) # set font text
        self.soogoe_score.setText(QCoreApplication.translate("Form", u"", None)) # set font text
        self.wrong_list_text.setText(QCoreApplication.translate("Form", u"", None)) # set font text
    # retranslateUi

    # rfid switch display
    def switch_display(self, name):
        if self.arr_idx == 1:
             print("switch_display() => name : ", name)
             self.setupImageNum(2)
             # name_text visible
             
             self.name_text.setText(name) # set Name 
             self.arr_idx = 3
       
    def setupImage(self):
            pixmap1 = self.pix_arr[self.arr_idx]
            pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
            self.label.setPixmap(pixmap)

    def setupImageNum(self, num):
            self.arr_idx = num
            pixmap1 = self.pix_arr[self.arr_idx]
            pixmap = pixmap1.scaled(QSize(1152, 875), aspectMode=Qt.IgnoreAspectRatio)
            self.label.setPixmap(pixmap)
            print("SCREEN NUM " + str(num))

    # distance sensor switch display
    def sense_display(self):
        if self.arr_idx == 0:
            print("sense_display()")

            self.setupImageNum(1)

    def guideToggle(self):
        
        if self.arr_idx == 3:
            self.arr_idx = 4
        else:
            self.arr_idx = 3

        self.setupImage()

        # name_text invisible
        self.name_text.setText(QCoreApplication.translate("Form", "", None)) # set Name
   
