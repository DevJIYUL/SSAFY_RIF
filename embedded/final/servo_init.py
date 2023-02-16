import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(22, GPIO.OUT)

pwm = GPIO.PWM(22, 50)
pwm.start(12)
time.sleep(1)
pwm.stop()
GPIO.cleanup()
  



