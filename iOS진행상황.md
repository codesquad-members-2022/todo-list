# TODO List

- 단계별로 미션을 해결하고 리뷰를 받고나면 readme.md 파일에 주요 작업 내용(바뀐 화면 이미지, 핵심 기능 설명)과 완성 날짜시간을 기록한다.
- 실행한 화면을 캡처해서 readme.md 파일에 포함한다.

## 작업내역

| 시작날짜   | 번호  | 내용                                   | 비고 |
| ---------- | :---- | -------------------------------------- | ---- |
| 2022.04.05 | Step4 | 네트워크 테스트를 위한 MockSession구현 |      |
| 2022.04.05 | Step3 | 새로운 카드 등록 팝업 구현             |      |
| 2022.04.05 | Step2 | 메인화면 기능 구현                     |      |
| 2022.04.04 | Step1 | 메인화면 레이아웃 구성                 |      |

------

## [Step4] 네트워크 테스트를 위한 MockSession구현

### 체크리스트 

- [x] 서버가 준비 되기 전 네트워크 테스트를 위한 MockSession 구현
- [ ] URLSessionProtocol을 준비하고, 채택하여 사용
- [ ] 테스트 시, 임의로 성공과 실패를 결정 할 수 있고, 원하는 데이터를 받아 
  실제 네트워크 통신하듯이 사용가능

### 핵심기능

* 실제 서버와 연결 전 서버없이 테스트를 할 수 있

------

## [Step3] 새로운 카드 등록 팝업 구현

### 체크리스트 

- [x] 새로운 카드 등록 팝업 구현
- [x] 팝업 ViewController 구현
- [x] 내부 UI 제작
- [x] 팝업UI 기능 구현
  - [x] 문자입력
  - [x] 확인/취소 버튼
  - [x] 종료 후 데이터 처리


### 핵심기능

* +버튼을 누르면 팝업이 뜨고, 내용입력 후 등록, 입력된 데이터를 원하는 곳으로 전달

### 결과화면

![Simulator Screen Recording - iPad Pro (12 9-inch) (5th generation) - 2022-04-06 at 09 59 19](https://user-images.githubusercontent.com/5019378/161874966-b96b48a7-f01a-47aa-9063-40fc010421d3.gif)

------

## [Step2] 메인화면 기능구현

### 체크리스트 

- [x] ColumnViewController
  - [x] 테이블 뷰를 사용하여 CardView 출력
  - [x] Card 갯수 출력
  - [x] UI 이벤트 연결
- [x] CardView
  - [x] 타이틀, 내용, 캡션 출력

- [x] 요구사항에 맞게끔 UI의 간격이나, 기능을 구현

### 핵심기능

* 메인화면에 필요한 UI가 임의로 동작하게끔 구현

### 결과화면

![Simulator Screen Recording - iPad Pro (12 9-inch) (5th generation) - 2022-04-06 at 09 54 24](https://user-images.githubusercontent.com/5019378/161874562-72ecf533-53dc-45e4-8c34-a92d67526197.gif)

------

## [Step1] 메인화면 레이아웃 구성

### 체크리스트 

- [x] 메인화면 레이어 정리
  - [x] 상단 타이틀 바
  - [x] ColumnViewController 제작
  - [x] CardView 제작
  
