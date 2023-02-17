### 🖼 욜로 모델을 활용한 이미지 분석

#### 사용 데이터셋

프로젝트의 목적성에 비추어 보았을 때, "**실제 SSAFY 강의장에서 발생하는 쓰레기**"를 대상으로 데이터셋을 만드는 것이 적합하다고 판단하였고, 데이터셋을 직접 제작하였습니다.

- **사용 이미지** : SSAFY 쓰레기통에서 발생한 커피 쓰레기 사진 **253**장

|예시 이미지 1| 예시 이미지 2|
|:--:|:--:|
|![image_example_1](https://user-images.githubusercontent.com/47023884/219288159-fe35b76f-b21a-463b-b18f-f26d9421c266.jpg)|![image_example_2](https://user-images.githubusercontent.com/47023884/219288164-ce35c846-68f7-467d-99d3-8b1b2f6a8b7d.jpg)|

- **클래스** 

색이 있는 플라스틱은 재활용이 어렵다는 점에 착안하여 <u>색의 유무</u>에 따라 우선적으로 클래스를 분류하였으며, 빨대와 컵홀더와 같은 기타 부산물 역시 분류하고자 하는 클래스에 추가하였습니다.

|id| 클래스명 |설명|
|:--:|:--:|:--:|
|0|clear plastic cup| 별도의 프린팅이 되어있지 않은 투명한 플라스틱 커피컵 (ex. **매머드커피**) |
|1|clear plastic cup lid| 별도의 프린팅이 되어있지 않은 투명한 플라스틱 커피컵의 뚜껑 |
|2|colored plastic cup lid| 색이 입혀진 투명한 플라스틱 커피컵의 뚜껑 (ex. **바나프레소**)|
|3|white plastic cup lid| 흰색의 불투명한 플라스틱 커피컵의 뚜껑|
|4|plastic bottle| 라벨이 제거된 투명한 상테의 페트병 |
|5|plastic bottle labeled|라벨이 제거되지 않은 상태의 페트병|
|6|plastic bottle cap| 페트병의 뚜껑 |
|7|paper cup| 인쇄여부를 구분하지 않는 종이컵 |
|8|straw| 재질을 구분하지 않은 빨대 |
|9|inked plastic cup| 별도의 프린팅이 입혀진 투명한 플라스틱 커피컵 (ex. **바나프레소**, **스타벅스**)|
|10|label|페트병 라벨|
|11|litter|기타 일반 쓰레기 (커피컵 스티커) |
|12|paper cup holder| 종이 컵홀더 |
|13|plastic cup with cup holder| 인쇄 및 색의 여부를 고려하지 않는, 컵홀더가 씌워진 플라스틱 컵|
|14|paper cup with cup holder|컵홀더가 씌워진 종이 컵|

- **데이터 증강**
1. RandomHorizontapFlip
2. RandomCrop (0% ~ 20%)
3. Rotation (-15 deg ~ +15 deg)

![class_distribution](https://user-images.githubusercontent.com/47023884/219288155-56c514ef-aa34-442a-b098-7e4320e45f64.png)

#### 사용 모델

모델은 [yolov5](https://github.com/ultralytics/yolov5)을 사용하였으며, 데이터 증강으로 얻은 약 800장의 사진을 85(train) : 10(valid) : 5(test)  비율로 나눠 학습하였습니다.

5개의 Pretrained Model 에 대하여 각각 BackBone Layer를 고정시켜 100 epochs씩 학습하였으며, 과적합을 방지하기 위해 15 epoch 이상 성능의 개선이 없으면 학습을 종료하는 Early Stopping 기법을 사용하였습니다.

|model|mAP$_{0.5}$ (last / best)|mAP$_{0.5:0.95}$ (last / best)|
|:--:|:--:|:--:|
|yolov5s6| **0.92241** / 0.94122|0.60246 / 0.65301|
|yolov5m6|0.91761 / 0.93395 |0.5427 / 0.67914|
|yolov5s|0.9152 / 0.92468|0.53308 / 0.60605|
|yolov5m|0.9156 / 0.93853| 0.63864 / 0.66291|
|yolov5x|0.91881 / **0.94299** |**0.72446** / **0.74659**|

이후 모델의 성능과 사진을 인식하고 결과를 출력할 때까지의 시간을 모두 고려해보았을 때, yolov5s6가 프로젝트에 가장 적합하다고 판단하여 해당 모델을 본 프로젝트에 적용하였습니다.

#### 학습 결과

|예시 이미지 1| 예시 이미지 2|
|:--:|:--:|
|![pred_example_1](https://user-images.githubusercontent.com/47023884/219288167-ce305087-6f52-42fc-8978-2247fbe061d2.jpg)|![pred_example_2](https://user-images.githubusercontent.com/47023884/219288168-9691aa73-3054-4fce-8568-dafd5a25ac1b.jpg)|
