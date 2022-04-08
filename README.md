# todo-list

> 8조

## 팀원

| iOS                                   | BE                                    |
| ------------------------------------- | ------------------------------------- |
| [dale](https://github.com/sungju-kim) | [포키](https://github.com/Seokho-Ham) |
| [sally](https://github.com/sally4405) | [dave](https://github.com/nak253)     |

## 작업 내용

## iOS

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

## BE

### 요구사항

- [ ] 사용자가 GET 요청을 하면 카드의 목록을 반환한다.
- [x] 사용자가 POST 요청을 하면 Card 객체를 생성하여 DB에 저장하고, 생성된 객체를 반환한다.
  - [ ] 생성에 대한 이벤트 정보를 DB에 저장한다.
- [x] 사용자가 PUT 요청을 하면 URL로 받은 id 값으로 DB에서 객체를 찾아서 값을 수정한다.
  - [ ] 섹션 이동이면 이동에 대한 이벤트 정보를 DB에 저장한다.
  - [ ] 내용의 수정이면 수정에 대한 이벤트 정보를 DB에 저장한다.
- [x] 사용자가 DELETE 요청을 하면 요청 URL로 받은 id 값으로 DB에서 객체를 찾아서 삭제한다.
  - [ ] 삭제에 대한 이벤트 정보를 DB에 저장한다.

## 결과물

### iOS 화면

- 액션 기록 화면 제외 전체화면 뷰 구현
- 가로 세로 방향에 따라 stackview의 constant를 변경하여 테이블의 셀이 너무 작아지지 않도록 함
- 리스트 추가 카드는 임시로 메뉴 버튼 누르면 뜨도록 구현

<img width="640" src="https://user-images.githubusercontent.com/78553659/161928635-a1c003c4-e000-4c42-b75b-02975607d13c.gif">

### BE flow 그림

<img width="640" src="https://user-images.githubusercontent.com/45891045/162131159-9d1c7ba4-2e51-4f21-b91e-0de937bc5ce7.png">

## Document

- [기획서](https://www.figma.com/proto/vYGeE8xND8ZRkesKHLIc1b/%EB%AA%A8%EB%B0%94%EC%9D%BC_%ED%88%AC%EB%91%90%-EB%A6%AC%EC%8A%A4%ED%8A%B8?node-id=94%3A418&scaling=contain&page-id=94%3A414)
- [Ground Rule](https://github.com/sally4405/todo-list/wiki/Ground-Rule)
- [API](https://app.swaggerhub.com/apis-docs/Seokho-Ham/todolist/1.0.0)
