<img src="https://capsule-render.vercel.app/api?type=wave&color=c2e59c&height=300&section=header&text=RIF&fontSize=90&animation=fadeIn" />

# Recycling Is Fun

## 📜 프로젝트 설명

### 개요

- 일회용품 사용을 줄일 수 있게 합니다
- 일회용품을 사용한다면 제대로 분리수거하여 재사용할 수 있게 합니다

### 타겟

- 분리수거가 익숙하지 않은 사람
- 올바른 분리수거 방법이 헷갈리는 사람

### 배경

일상에서 주로 사용하는 플라스틱컵이 제대로 분리수거 되고 있지 않음

### 목적

직접 분리수거 해보면서 즉각 피드백을 통해 잘못된 분리수거 의식을 바로 잡기 위해 프로젝트를 기획

### 구성

- 분리수거 기기를 통해 플리스틱컵을 직접 분리수거 해보고 즉각 피드백 통해 분리수거 의식을 향상시킵니다.
- 웹 모바일에서 분리수거 이력에 대한 보상으로 게이미피케이션 요소를 추가하여 지속적으로 분리수거 기기 사용을 유도합니다.

## 🎬영상

## 🖼화면

|  |  |
| - | - |
| ![하얀 강아지](gif/archive_settging.gif)<p align="center"> **대표업적(메달)설정** </p>   | ![하얀 강아지](gif/ranking_login.gif)<p align="center"> **랭킹페이지_로그인시** </p>         |
| ![하얀 강아지](gif/login.gif)<p align="center"> **로그인** </p> | ![하얀 강아지](gif/main_more.gif)<p align="center"> **메인화면_더알아보기** </p> |
| ![하얀 강아지](gif/maueal.gif)<p align="center"> **사용방법** </p> | ![하얀 강아지](gif/use_log.gif)<p align="center"> **사용기록확인** </p> |  ![하얀 강아지](gif/maueal.gif) <p align="center"> **사용방법** </p>  
| ![하얀 강아지](gif/user_search.gif)<p align="center"> **사용자검색** </p> | ![하얀 강아지](gif/gacha.gif) <p align="center"> **뽑기5회** </p> |
| ![하얀 강아지](gif/user_search_after_select.gif)<p align="center"> **사용자검색후_프로필조회** </p>| ![하얀 강아지](gif/프로필화면_프로필이미지변경.gif)<p align="center"> **프로필화면_프로필이미지변경** </p> |

## 🙆효과

|    날짜    | 프로젝트 도입 이전 | 프로젝트 도입 이후 |
| :--------: | :----------------: | :----------------: |
| 2023.01.19 |     1개 / 15개     |                    |
| 2023.01.20 |     3개 / 19개     |                    |
| 2023.01.25 |     0개 / 10개     |                    |
| 2023.01.26 |     2개 / 16개     |                    |
| 2023.01.27 |     6개 / 15개     |                    |
| 2023.02.01 |     2개 / 17개     |                    |

---

## 🛠사용법

### 설치

#### 프론트 빌드

```
git clone -b only_pull_branch --single-branch https://lab.ssafy.com/s08-webmobile3-sub2/S08P12A501.git

cd S08P12A501/frontend/rif

// yarn 확인
yarn -v

// yarn이 없다면
npm install yarn

yarn install

// 설치 완료 후
yarn start
```

#### 서버 빌드

```
git clone -b only_pull_branch --single-branch https://lab.ssafy.com/s08-webmobile3-sub2/S08P12A501.git

cd S08P12A501/backend/rif

```

### 호환 브라우저

| ![chrome](https://user-images.githubusercontent.com/97886013/219284514-91a36357-4480-4876-9ae9-e0aec6de5841.png) | ![Samsung Internet](https://user-images.githubusercontent.com/97886013/219283822-d1113a38-ceba-4315-81f2-b988daa2635b.png) | ![Safari](https://user-images.githubusercontent.com/97886013/219284504-2c2e60d3-2dbc-4a6f-ba8d-3b5b6fccad3e.png) | ![Naver Whale](https://user-images.githubusercontent.com/97886013/219284513-cfec140f-4b4a-4529-b9ee-aad7458096c7.png) |
| ---------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| Lastest✔                                                                                                         | Lastest✔                                                                                                                   | Lastest✔                                                                                                         | Lastest✔                                                                                                              |

---

## 폴더 구조

#### 프론트엔드

```
├─public
│  ├─achievement
│  ├─badge
│  ├─howToUse
│  ├─navigationbar
│  ├─profile
│  └─ranking
└─src
    ├─API
    ├─Components
    ├─fonts
    ├─Pages
    ├─store
    └─UI
```

#### 백엔드

```
├─gradle
│  └─wrapper
└─src
    ├─main
    │  ├─java
    │  │  └─team
    │  │      └─a501
    │  │          └─rif
    │  │              ├─config
    │  │              │  ├─Jwt
    │  │              │  └─Swagger
    │  │              ├─controller
    │  │              │  ├─achievement
    │  │              │  ├─auth
    │  │              │  ├─badge
    │  │              │  └─member
    │  │              ├─domain
    │  │              │  ├─achievement
    │  │              │  ├─auth
    │  │              │  ├─badge
    │  │              │  ├─member
    │  │              │  ├─riflog
    │  │              │  └─role
    │  │              ├─dto
    │  │              │  ├─achievement
    │  │              │  ├─auth
    │  │              │  ├─badge
    │  │              │  ├─jwt
    │  │              │  ├─member
    │  │              │  └─riflog
    │  │              ├─exception
    │  │              ├─repository
    │  │              │  ├─achievement
    │  │              │  ├─auth
    │  │              │  ├─badge
    │  │              │  ├─member
    │  │              │  └─riflog
    │  │              └─service
    │  │                  ├─achievement
    │  │                  ├─auth
    │  │                  ├─badge
    │  │                  ├─member
    │  │                  └─riflog
    │  └─resources
    └─test
        └─java
            └─team
                └─a501
                    └─rif
                        ├─domain
                        │  └─riflog
                        ├─repository
                        │  ├─achievement
                        │  ├─badge
                        │  └─member
                        └─service
                            ├─member
                            └─riflog
```

#### 임베디드

```
├─background_img
├─final
│  ├─classify
│  ├─data
│  │  ├─hyps
│  │  ├─images
│  │  └─scripts
│  ├─models
│  │  ├─hub
│  │  ├─segment
│  │  └─__pycache__
│  ├─pts
│  ├─segment
│  ├─utils
│  │  ├─aws
│  │  ├─docker
│  │  ├─flask_rest_api
│  │  ├─google_app_engine
│  │  ├─loggers
│  │  │  ├─clearml
│  │  │  ├─comet
│  │  │  └─wandb
│  │  ├─segment
│  │  │  └─__pycache__
│  │  └─__pycache__
│  └─__pycache__
├─pi_img
└─yoloresult
```

---

## 시스템 구성도

![image](https://user-images.githubusercontent.com/97886013/219298816-617b36e4-8894-4739-afaf-de8b20f9158d.png)

---

## 🛢기술스택

|                                                                                                                                                                                                                                                                                                                                                                 임베디드                                                                                                                                                                                                                                                                                                                                                                  |                                                                                                                                                                                                                                                                                                                                                                             프론트 엔드                                                                                                                                                                                                                                                                                                                                                                              |                                                                                                                                                                                                                                                                                                                                                                                                                     백 엔드                                                                                                                                                                                                                                                                                                                                                                                                                     |                                                                                                  머신 러닝                                                                                                  |                                                                                                                                                                                                           인프라                                                                                                                                                                                                            |                                                                                                                                                                                                                                                                                      툴                                                                                                                                                                                                                                                                                      |
| :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://img.shields.io/badge/Raspberry Pi-A22846?style=for-the-badge&logo=Raspberry Pi&logoColor=white"> <img src="https://img.shields.io/badge/Arduino-3776AB?style=for-the-badge&logo=Arduino&logoColor=white"><img src="https://img.shields.io/badge/Python-00979D?style=for-the-badge&logo=Python&logoColor=white"> <img src="https://img.shields.io/badge/C-A8B9CC?style=for-the-badge&logo=C&logoColor=white"> <img src="https://img.shields.io/badge/Vim-019733?style=for-the-badge&logo=Vim&logoColor=white"> <img src="https://img.shields.io/badge/PyCharm-000000?style=for-the-badge&logo=PyCharm&logoColor=white"> <img src="https://img.shields.io/badge/PyQt-41CD52?style=for-the-badge&logo=qt&logoColor=white"> | <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=node.js&logoColor=white"> <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"> <img src="https://img.shields.io/badge/Redux-764ABC?style=for-the-badge&logo=Redux&logoColor=white"> <img src="https://img.shields.io/badge/React Router-CA4245?style=for-the-badge&logo=React Router&logoColor=white"> <img src="https://img.shields.io/badge/Create React App-09D3AC?style=for-the-badge&logo=Create React App&logoColor=white"> <img src="https://img.shields.io/badge/yarn-2C8EBB?style=for-the-badge&logo=yarn&logoColor=white"> <img src="https://img.shields.io/badge/mui-007FFF?style=for-the-badge&logo=mui&logoColor=white"> | <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/Redis-FF0000?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="http://jwt.io/img/logo-asset.svg" style="width:70px"> | <img src="https://img.shields.io/badge/PyTorch-EE4C2C?style=for-the-badge&logo=PyTorch&logoColor=white"> <img src="https://img.shields.io/badge/YOLO-00FFFF?style=for-the-badge&logo=YOLO&logoColor=white"> | <img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=Ubuntu&logoColor=white"> <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"> <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white"> <img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white"> | <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white"> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"> <img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white"> <img src="https://img.shields.io/badge/GitLab-FC6D26?style=for-the-badge&logo=GitLab&logoColor=white"> <img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white"> |

## 개발 환경

### BE

**툴**

- IntelliJ IDE

**언어 및 라이브러리**

- Springboot 2.7.7
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- Redis
- MariaDB
- Swagger 3.0.0

### FE

**툴**

- visual studio code

**언어 및 라이브러리**

- Node.js (18.12.1)
- yarn (1.22.19)
- react (18.2.0)
- react-redux (8.0.5)
- react-router(6.7.0)
- redux-persist (6.0.0)
- reduxjs/toolkit (1.9.2)
- react-swipeable-views (0.14.0)
- axios (1.2.4)
- mui (5.11.0)

### EMB

**툴**

- MobaXterm (Personal Edition v22.3)
- VNC Viewer (6.22.826)
- PyCharm (2022.3.1)
- Vim (8.2)

**언어 및 라이브러리**

- Python (3.9.2)
- C (gnu17)
- gcc (10.2.1)
- Arduino IDE (1.8.13)
- PyPi (5.15.2.1)
- mfrc522 (0.0.7)
- RPi.GPIO (0.7.0)
- gpiozero (1.6.2)
- picamera2 (0.3.8)
- PyTorch (1.13.1)
- requests (2.25.1)
- pySerial (3.5b0)

**라즈베리파이 및 디스플레이**

- Raspberry Pi - Pi 4 Model B (1)
- Monitor - 7inch HDMI LCD Monitor (1024X600) (1)
- Arduino - Arduino Uno Rev3 (1)

**센서 모듈명**

- 카메라 모듈 - Pi camera V2.1 (1)
- 초음파 거리센서 모듈 - HC-SR04P (1)
- RFID 모듈 - RFID-RC522 (1)
- 메탈기어 디지털 서보모터 - MG996R (2)
- 스피커 모듈 - ELB060302 (1)
- 아케이드 버튼 - 30mm Arcade Game Machine Switch (SZH-ZR001, SZH-ZR004) (2)
- LED - RED, GREEN, YELLOW (3)
- 전선 (연장용)
- 점퍼 케이블

**기기 패키징**

- 하드보드지

### CI/CD

- Jenkins
- AWS EC2
- NGINX
- SSL

---

## 문서

API 명세서

[임베디드 회로도](https://lab.ssafy.com/s08-webmobile3-sub2/S08P12A501/-/blob/master/docs/임베디드회로도.png)

[YOLO 모델 상세](https://lab.ssafy.com/s08-webmobile3-sub2/S08P12A501/-/blob/master/docs/yolo.md)
