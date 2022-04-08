# todo-list
> 8조

## 팀원

|iOS|BE|
|---|---|
|[dale](https://github.com/sungju-kim)|[포키](https://github.com/Seokho-Ham)|
|[sally](https://github.com/sally4405)|[dave](https://github.com/nak253)|


## 작업 내용

### 리스트 (Table View)
- xib로 재활용 리스트 만들기
  - [x] 제목/배지/추가 버튼, 카드 목록
  - [x] 배지 모서리 없이 표시하고 숫자가 늘어나면 iOS 기본 배지처럼 가운데 영역이 길어진다.

### 리스트 카드
- xib로 재활용 카드 만들기
  - [x] 제목, 본문 (3줄까지)
  - [x] 셀 높이 self-resizing

### 리스트 카드 추가/편집 화면
- xib로 재활용 뷰 만들기
  - [x] 제목, 본문, 취소/등록


## 결과물

### iOS 화면
- 액션 기록 화면 제외 전체화면 뷰 구현
- 가로 세로 방향에 따라 stackview의 constant를 변경하여 테이블의 셀이 너무 작아지지 않도록 함
- 리스트 추가 카드는 임시로 메뉴 버튼 누르면 뜨도록 구현 (아직 지워지는 기능 미구현)
- 현재는 액션 보드 화면을 처음에 뜨도록 만들었고, 지울 수 있지만 다시 띄울 수 없음 

<img width="640" src="https://user-images.githubusercontent.com/45891045/162369093-670ca58e-9156-4f05-bffd-60ccdd9f02d1.gif">

### iOS 뷰 구조와 네이밍
<img width="640" src="https://user-images.githubusercontent.com/45891045/162370382-9ac206af-9077-48a5-90b0-80916a03310a.png">

### BE flow 그림
<img width="640" src="https://user-images.githubusercontent.com/45891045/162131159-9d1c7ba4-2e51-4f21-b91e-0de937bc5ce7.png">


## 고민과 해결

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


## Document

- [기획서](https://www.figma.com/proto/vYGeE8xND8ZRkesKHLIc1b/%EB%AA%A8%EB%B0%94%EC%9D%BC_%ED%88%AC%EB%91%90%-EB%A6%AC%EC%8A%A4%ED%8A%B8?node-id=94%3A418&scaling=contain&page-id=94%3A414)
- [Ground Rule](https://github.com/sally4405/todo-list/wiki/Ground-Rule)
- [API](https://app.swaggerhub.com/apis-docs/Seokho-Ham/todolist/1.0.0)