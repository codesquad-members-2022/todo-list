# todo-list
> 8조

## 팀원

|iOS|BE|
|---|---|
|[dale](https://github.com/sungju-kim)|[포키](https://github.com/Seokho-Ham)|
|[sally](https://github.com/sally4405)|[dave](https://github.com/nak253)|

---

## iOS 작업 내용

### 뷰
- 리스트 (Table View): xib로 재활용 리스트 만들기
  - 제목/배지/추가 버튼, 카드 목록
  - 배지 모서리 없이 표시하고 숫자가 늘어나면 iOS 기본 배지처럼 가운데 영역이 길어진다.
- 리스트 카드: xib로 재활용 카드 만들기
  - 제목, 본문 (3줄까지)
  - 셀 높이 self-resizing
- 리스트 카드 추가/편집 화면: xib로 재활용 뷰 만들기
  - 제목, 본문, 취소/등록

### 서버 통신
- 서버에서 데이터 받아오기
  - 데이터 파싱
- model: TaskCard, TaskCardList, TaskBoard

### 기능
- 카드 수정하기, 이동하기, 삭제하기 
- 수정하기 선택시 editCardView에 이전 정보 띄우기
- 활동 기록 화면 데이터 받아오기

---

## 결과물

### iOS 화면
- 가로 세로 방향에 따라 stackview의 constant를 변경하여 테이블의 셀이 너무 작아지지 않도록 함
- 카드 추가, 수정, 이동, 삭제시 전체 뷰 리로딩하여 카드 불러오기 구현
- 타이틀이 변경되거나 한글자 이상일 때만 추가, 수정 가능
- 카드를 슬라이드하거나 길게 눌러서 삭제 가능
- 카드를 길게 누르면 해당 섹션 외의 섹션으로 이동 가능
- 카드 추가, 수정, 이동, 삭제 활동 기록하는 화면 구현

|활동 기록 화면|가로모드/세로모드 전환|
|---|---|
|<img width="320" src="https://user-images.githubusercontent.com/45891045/163533005-39cf4378-9758-4592-aff7-259d893e28f6.gif">|<img width="320" src="https://user-images.githubusercontent.com/45891045/163533029-ff163a99-a22f-4ca6-8535-993612289e11.gif">|

|카드 추가하기|카드 삭제하기|
|---|---|
|<img width="320" src="https://user-images.githubusercontent.com/45891045/163531409-97833220-29c6-4dae-a2fc-3f1334246854.gif">|<img width="320" src="https://user-images.githubusercontent.com/45891045/163531902-49237e41-9f6b-447b-92cb-fca1348638dc.gif">|

|카드 수정하기|카드 이동하기|
|---|---|
|<img width="320" src="https://user-images.githubusercontent.com/45891045/163531526-fd455cec-fc8c-4f5c-a1d3-1c6d0740a68a.gif">|<img width="320" src="https://user-images.githubusercontent.com/45891045/163531821-5b16be10-c761-4992-8ff1-07a70fa2788e.gif">|

### flow
|iOS 뷰 구조와 네이밍|BE flow 그림|
|---|---|
|<img width="320" src="https://user-images.githubusercontent.com/45891045/162370382-9ac206af-9077-48a5-90b0-80916a03310a.png">|<img width="320" src="https://user-images.githubusercontent.com/45891045/162131159-9d1c7ba4-2e51-4f21-b91e-0de937bc5ce7.png">|

---

## 고민과 해결

## iOS

### xib 이름 변경 과정에서 다음과 같은 오류가 발생
```bash
- 'NSInternalInconsistencyException', reason: '-[UIViewController _loadViewFromNibNamed:bundle:] loaded the TaskCardListView nib but the view outlet was not set
- this class is not key value coding-compliant for the key tableView.' [duplicate]
```

1. .xib 파일 이름변경 및 nib name 변경
2. .xib 파일의 file's owner 커스텀 클래스 -> UIView 가 아닌 UIViewController 로 변경
3. file's owner -> TaskCardListView outlet 연결
4. file's owner 커스텀 클래스 삭제
5. nib 불러오는 Bundle.name의 owner를 nil -> self 로 변경 (UIViewController)
6. Addsubview 삭제
-> 해결은 했으나 이유를 아직 모르는 상태

### action board hidden시 하위에 있는 버튼이 터치되지 않음
- Action board 를 띄우고 없애는 기능을 .isHidden 속성을 이용해 해결하려고 했습니다.
1. story board 에서 container view 를 삽입하고 해당 뷰의 클래스를 ActionBoardViewController로 지정
2. 메뉴 버튼 터치시 MyTaskViewController에서 actionBoard (container view IBOutlet)의 .isHidden 속성을 false로 변경
3. actionBoard가 띄워지도록 한후, x 버튼을 터치했을때  ActionBoardViewController에서 `self.view.isHidden = true` 를 통해 actionBoard를 사라지게 함
4. 이후 다시 메뉴버튼을 눌렀을때 actionBoard 가 띄워져야 하지만 안띄워져서 hierarchy 디버깅을 해보니 isHidden 이 되지않은 UIView 가 남아있었음

### edit card view를 여러번 띄웠을때 카드가 여러번 추가됨
- editCardViewController가 로드될때 observer로 등록이 되는데, dismiss 시에도 observer에서 사라지지 않고 메모리에 참조값이 남아있다 라고 생각을 했습니다.
1. deinit에 디버깅을 찍어보니 호출되지 않음
2. viewWillDisappear에서 observer를 삭제해보았지만 해결되지 않음
3. viewWillDisappear에서 removeFromParent를 호출해 보았지만 해결되지 않음
4. NotificationCenter 사용부분을 delegate로 변경 - 해결

### 서버에서 get 해올때 taskBoard 순서가 매번 바뀌는 현상
- 서버에서 get 해온 타입이 Dictionary 타입이라서 바뀔수 밖에 없다고 생각을 했습니다.
1. 서버의 데이터구조를 Dictionary 타입이 아니게 변경을 해야하나 고민
2. 섹션의 이름을 서버에 저장하기 위해서는 Dictionary 타입을 사용해야 한다고 생각
3. 로컬에서 자체적으로 순서를 정해주기로 결정(섹션 타이틀 고정)
-> "해야할 일", "하고 있는 일", "완료된 일" 세가지의 섹션만 사용해서 순서대로 배치하게 해결

## BE

### 도메인 객체를 새로운 Dto 객체로 매핑하는 과정에서 생기는 오버헤드

- 성능을 어느정도 포기하더라도 Dto 객체로 매핑해서 반환해야 하는가에 대해 고민했습니다.

클라이언트가 카드 목록 조회를 요청했을때 List에 도메인 객체를 넣어서 반환하도록 구현했습니다.
하지만 아래와 같은 이유로 도메인 객체를 반환하는게 좋지 않다는 사실을 알게되었습니다.
   
- 모든 계층에서 도메인 객체만 사용할 경우, 비지니스 로직과 크게 상관없는 곳에서 실수로 원치 않은 값으로 변경될 여지가 있습니다.
- 도메인 객체가 DB의 테이블에 존재하는 Column들에 대한 필드뿐만 아니라
View에서 필요한 필드를 모두 가지게 된다면 도메인 객체가 필요 이상의 정보를 가지게 되어 설계상 좋지 않습니다.

이러한 이유로 도메인 객체를 Dto 객체로 매핑하여 반환하는 것이 적절할 수 있지만 도메인 객체를 새로운 DTO 데이터 클래스로 매핑하는 과정에서 오버헤드가 발생하게 됩니다.

이러한 오버헤드를 감수하고 도메인 객체를 Dto 객체로 매핑하여 반환해야 할지 고민해보았습니다.
결론적으로 위에 서술한 두 가지 상황은 저희의 코드에 해당하지 않았고 저희는 도메인 객체를 반환하기로 결정하였습니다.

### @RequestBody 어노테이션을 통해 요청으로 넘어온 body를 매핑하는 과정에 대한 학습

- 학습하게 된 배경
    - RequestBody로 넘어온 데이터를 RequestDto 객체에 매핑하는 과정에서 아래와 같은 에러가 발생했습니다.
        ``` 
        com.fasterxml.jackson.databind.exc.InvalidDefinitionException
        ```
        해당 에러를 해결하기 위해서 ObjectMapper가 매핑하는 과정에서 default 생성자의 필요성에 대해서 학습하게 되었습니다.
    
- 학습한 내용
    - 일반적인 상황에서는 default constructor가 필요합니다. 이를 전제하여 기본 생성자를 활용하여 Java Reflection을 통해 프로퍼티를 주입하는 과정을 거쳐서 DTO를 생성하기 때문입니다.
    - @JsonProperty, @JsonAutoDetect 등을 사용한 Property 기반 클래스이거나, 생성자가 위임된 경우라면 필요하지 않습니다. 우리는 여기서 @getter와 @Setter 를 사용하였기 때문에 Property 기반 클래스라고 해석할 수 있습니다.

- 결론
    - 결과적으로 저희 코드에는 RequestDto에 getter를 정의하였기 때문에 default 생성자를 필요하지 않습니다. 하지만 같은 에러는 계속 발생하였고 결국 local의 문제임을 깨닫게 되었습니다.
    - 학습한 내용이 문제 해결에 도움이 직접적으로 도움이 되진 않았지만 RequestBody로 넘어온 데이터를 객체에 매핑하는 흐름을 알게 되었고 앞으로의 코드 작성에서 적절한 getter와 setter 작성에 많은 도움이 되었습니다.

---

## 해결하지 못한 것들

## iOS
- 카드 글자 수 제한
- 카드 입력시 크기 동적으로 변하기
- 키보드 나타날 때, 카드 입력 부분 같이 올라가게 하기
- activity board text 볼드 추가
- 모든 액션마다 모든 카드를 get 해오는 문제

---

## Document

- [기획서](https://www.figma.com/proto/vYGeE8xND8ZRkesKHLIc1b/%EB%AA%A8%EB%B0%94%EC%9D%BC_%ED%88%AC%EB%91%90%-EB%A6%AC%EC%8A%A4%ED%8A%B8?node-id=94%3A418&scaling=contain&page-id=94%3A414)
- [Ground Rule](https://github.com/sally4405/todo-list/wiki/Ground-Rule)
- [API](https://app.swaggerhub.com/apis-docs/Seokho-Ham/todolist/1.0.0)