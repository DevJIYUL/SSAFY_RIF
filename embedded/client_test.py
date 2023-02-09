import socket
import json

HOST = '127.0.0.1'
PORT = 9999


client_soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

client_soc.connect((HOST, PORT))

msg = input("INPUT MSG : ")

client_soc.sendall(msg.encode())

data = client_soc.recv(1024)

print('Received', repr(data.decode()))

# print result
print("=================print result======================")
with open('/home/parkdoyun/yoloresult/result.json', 'r') as file:
    data = json.load(file)
    print(data)

client_soc.close()
