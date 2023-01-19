import RPi.GPIO as gpio
import time

buzzer = 18
gpio.setmode(gpio.BCM)
gpio.setup(buzzer, gpio.OUT)
gpio.setwarnings(False)

freq = 262
pwm = gpio.PWM(buzzer, freq)
pwm.start(50.0)


for i in range(0,3):
    pwm.start(50.0)
    while True:
        pwm.ChangeFrequency(freq)
        time.sleep(0.005)
        freq += 1
        if(freq>500):
            break

    time.sleep(0.1)
    pwm.stop() 
    time.sleep(0.2)
    freq = 262
    

pwm.stop()
gpio.cleanup()
