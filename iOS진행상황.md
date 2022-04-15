# TODO List

------

- 단계별로 미션을 해결하고 리뷰를 받고나면 readme.md 파일에 주요 작업 내용(바뀐 화면 이미지, 핵심 기능 설명)과 완성 날짜시간을 기록한다.
- 실행한 화면을 캡처해서 readme.md 파일에 포함한다.

------

## 작업내역

| 시작날짜   | 번호   | 내용                                     | 비고 |
| ---------- | :----- | ---------------------------------------- | ---- |
| 2022.04.11 | Step10 | 테이블 셀의 드래그 앤 드롭 구현          |      |
| 2022.04.11 | Step9  | 컬럼 테이블 셀의 trailingSwipeAction구현 |      |
| 2022.04.09 | Step8  | 테스트 네트워크 리펙토링                 |      |
| 2022.04.08 | Step7  | 서브메뉴 테스트 네트워크 연결            |      |
| 2022.04.07 | Step6  | 서브메뉴 기능구현                        |      |
| 2022.04.06 | Step5  | 네트워크 테스트를 사용한 UI 연결         |      |
| 2022.04.05 | Step4  | 네트워크 테스트를 위한 MockSession구현   |      |
| 2022.04.05 | Step3  | 새로운 카드 등록 팝업 구현               |      |
| 2022.04.05 | Step2  | 메인화면 기능 구현                       |      |
| 2022.04.04 | Step1  | 메인화면 레이아웃 구성                   |      |

------

## [Step10] 테이블 셀의 드래그 앤 드롭 구현

### 체크리스트 

- [x] 해당 기능을 구현하기 위해 R&D
- [x] 테이블 뷰에서 제공하는 Drag&Drop 기능 학습
- [x] 드래그를 위한 NSItemProvider 모델 제작
- [x] 뷰끼리 Drag&Drop 기능 테스트
- [x] 테스트 네트워크 연동

### 핵심기능

* 각 컬럼의 카드를 Drag&Drop하여 원하는 컬럼으로 이동 할 수 있다

### 결과화면

![Simulator Screen Recording - iPad Pro (12 9-inch) (5th generation) - 2022-04-11 at 17 54 18](https://user-images.githubusercontent.com/5019378/162701445-8b4d2d4a-2961-4c60-a63a-a649a8335f82.gif)

------

## [Step9] 컬럼 테이블 셀의 trailingSwipeAction구현

### 체크리스트 

- [x] 해당 기능을 구현하기 위해 R&D
- [x] 테이블 뷰에서 제공하는 trailingSwipeAction 학습
- [x] 테이블 뷰의 구현 방식변경
  - [x] 기존 row만 사용하는 방식에서 section을 사용
- [x] 스와이프 액션 기능 구현 및 버튼 추가
- [x] 버튼 이벤트 연결 및 테스트 네트워크 연결

### 핵심기능

* 셀을 왼쪽으로 스와이프하여 메뉴를 보고 선택 할 수 있다

### 결과화면

![Simulator Screen Recording - iPad Pro (12 9-inch) (5th generation) - 2022-04-11 at 17 53 09](https://user-images.githubusercontent.com/5019378/162701480-4afb5db8-07bf-48c7-a3f0-b96bfcf338c6.gif)

------

## [Step8] 테스트 네트워크 리펙토링

### 체크리스트 

- [x] 테스트 네트워크에 사용할 Database 정의

- [x] API요청을 보내고,  테스트 네트워크를 통해 DB 갱신 및 응답

- [x] 응답값에 따라 클라이언트가 처리되도록 작업

- [x] 실제서버 - 테스트 변경 시 간단한 수정만으로 스위칭되도록 작업

  ```swift
  //request(.loadColumns, isSucccess: true) - 테스트 네트워크 성공
  //request(.loadColumns, isSucccess: false) - 테스트 네트워크 실패
  //request(.loadColumns) - 실제 네트워크 적용
  func loadColumns() -> AnyPublisher<ApiResult<[Column], SessionError>, Never> {
    request(.loadColumns, isSucccess: true)
    .map { $0.decode([Column].self) }
    .eraseToAnyPublisher()
  }
  ```

### 핵심기능

* 실제 서버통신과 같은 API를 호출하여 원하는 결과값을 얻을 수 있다
  * 성공 & 실패 시 원하는 값이나 원하는 실패처리로 테스트 가능
* 서버가 아직 준비중인 상태에서도 서로의 Model만 정의되어 있으면 테스트를 할 수 있다

### 결과화면

![Simulator Screen Recording - iPad Pro (12 9-inch) (5th generation) - 2022-04-10 at 19 20 13](https://user-images.githubusercontent.com/5019378/162613679-d61c2713-1aa7-4c2e-9abb-2f958d197f61.gif)

------

## [Step7] 서브메뉴 테스트 네트워크 연결

### 체크리스트 

- [x] ContentMenu 구현
  - [x] 완료한 일로 이동 테스트 네트워크 구현
  - [x] 수정하기 테스트 네트워크 구현
  - [x] 삭제하기 테스트 네트워크 구현
  - [x] 새카드 추가하기 테스트 네트워크 구현

### 핵심기능

* 테스트 네트워크를 통해 각 컬럼에 새로운 카드를 추가 할 수 있다
* 테스트 네트워크를 통해 선택한 카드를 완료됨으로 이동할 수 있다
* 테스트 네트워크를 통해 선택한 카드를 수정, 삭제 할 수 있다

------

## [Step6] 서브메뉴 기능구현

### 체크리스트 

- [x] ContentMenu 구현
  - [x] 완료한 일로 이동
  - [x] 수정하기
  - [x] 삭제하기
  - [x] 새카드 추가하기

### 핵심기능

* 각 컬럼에 새로운 카드를 추가 할 수 있다
* 선택한 카드를 완료됨으로 이동할 수 있다
* 선택한 카드를 수정, 삭제 할 수 있다

------

## [Step5] 네트워크 테스트를 사용한 UI 연결

### 체크리스트 

- [x] 네트워크 테스트 시 사용할 데이터 모델 정의

  ```swift
  //테스트 네트워크에서 사용할 카드 데이터 모델
  class MockCard: Decodable, CustomStringConvertible, Equatable {
      let id: Int
      public private(set) var title: String
      public private(set) var content: String
      let author_system: String
      public private(set) var column_name: MockColumnType
      public private(set) var order_id: Int
  }
  ```

  ```swift
  //테스트 네트워크에서 사용 할 클라이언트 데이터 모델
  struct ClientColumn: Encodable {
      let type: MockColumnType
      let cards: [ClientCard]
  }
  
  struct ClientCard: Encodable {
      let id: Int
      let title: String
      let content: String
      let caption: String
  }
  ```

  

- [x] 임의로 생성한 데이터 모델을 사용하여 MockSession을 통해 네트워크 통신 테스트

- [x] 모델과 View의 연결


### 핵심기능

* 임의로 생성한 데이터모델을 사용하여, 실제 UI와 연결하여 결과를 볼 수 있다

### 결과화면

<img src="https://user-images.githubusercontent.com/5019378/161911818-29e61e1c-88b7-456f-92e3-c847194ed1c9.png" alt="Simulator Screen Shot - iPad Pro (12 9-inch) (5th generation) - 2022-04-06 at 15 43 34" style="zoom:30%;" />

------

## [Step4] 네트워크 테스트를 위한 MockSession구현

### 체크리스트 

- [x] 서버가 준비 되기 전 네트워크 테스트를 위한 MockSession 구현
- [x] URLSessionProtocol을 준비하고, 채택하여 사용
- [x] 테스트 시, 임의로 성공과 실패를 결정 할 수 있고, 원하는 데이터를 받아 
  실제 네트워크 통신하듯이 사용가능

### 핵심기능

* 실제 서버와 연결 전 서버없이 테스트를 할 수 있다

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
