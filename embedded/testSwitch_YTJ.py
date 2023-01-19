# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'test.ui'
##
## Created by: Qt User Interface Compiler version 6.4.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide2.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide2.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide2.QtWidgets import (QApplication, QLabel, QSizePolicy, QWidget)
from PySide2.QtCore import QSize, Qt

class Ui_Form(object):
    arr_idx = 0 # 배열 가리킬 인덱스
    pix_arr = [] # 사진 넣을 배열

    def setupUi(self, Form):
        # 사진 설정
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/daegi.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/finish.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/guide1.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/guide2.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/jaeboonryu.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/name.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/not_OK.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/OK.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/partial_OK.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/soogoe.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/start.png'))
        self.pix_arr.append(QPixmap('/home/parkdoyun/background_img/taking_picture.png'))
        pixmap1 = self.pix_arr[self.arr_idx]
        
        # 비율 조정
        pixmap = pixmap1.scaled(QSize(1280, 980), aspectMode=Qt.IgnoreAspectRatio)

        if not Form.objectName():
            Form.setObjectName(u"Form")
        Form.resize(1280, 980)
        # Form.showFullScreen();
        self.label = QLabel(Form)
        self.label.setObjectName(u"label")
        
        # 사진 넣는 label 크기 설정, 사진 넣기
        self.label.setGeometry(QRect(0, 0, 1280, 980))
        self.label.setPixmap(pixmap)

        self.retranslateUi(Form)

        QMetaObject.connectSlotsByName(Form)

        # 사진 클릭 이벤트 처리 위해
        #self.origin_photo = True
        
        # label 클릭 이벤트 추가
        self.label.mousePressEvent = self.photo_toggle

    # setupUi

    def retranslateUi(self, Form):
        Form.setWindowTitle(QCoreApplication.translate("Form", u"Form", None))

    def photo_toggle(self, event):

        #if self.origin_photo:
        print("CLICK")
        self.arr_idx = (self.arr_idx + 1) % 12

        pixmap1 = self.pix_arr[self.arr_idx]
        pixmap = pixmap1.scaled(QSize(1280, 980), aspectMode=Qt.IgnoreAspectRatio)
        self.label.setPixmap(pixmap)

            #self.origin_photo = True

    # button event => image change
    def pressed1(self):
        print("PRESS 1")
        self.arr_idx -= 1
        if self.arr_idx == -1:
            self.arr_idx = 2
        
        pixmap1 = self.pix_arr[self.arr_idx]
        pixmap = pixmap1.scaled(QSize(1280, 980), aspectMode=Qt.IgnoreAspectRatio)
        self.label.setPixmap(pixmap)
    
    def pressed2(self):
        print("PRESS 2")
        self.arr_idx = (self.arr_idx + 1) % 12
        
        pixmap1 = self.pix_arr[self.arr_idx]
        pixmap = pixmap1.scaled(QSize(1280, 980), aspectMode=Qt.IgnoreAspectRatio)
        self.label.setPixmap(pixmap)
        
    def customer(self):
        print("Customer!!")
        if self.arr_idx == 0:
            self.arr_idx = 1

        pixmap1 = self.pix_arr[self.arr_idx]
        pixmap = pixmap1.scaled(QSize(1280, 980), aspectMode=Qt.IgnoreAspectRatio)
        self.label.setPixmap(pixmap)



    # retranslateUi



