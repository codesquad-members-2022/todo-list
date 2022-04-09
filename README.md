# todo-list

## 팀원소개

- Muffin

- Jinnie

---

### 협업 전략

1. 각자의 브랜치(Muffin, Jinnie)에서 맡은 기능 개발

2. 개발한 기능을 공통으로 사용되어지는 원격 저장소의 team-32 브랜치에 PR

3. 기능이 더해진 team-32 브랜치를 다시 각자의 로컬 환경에서 pull로 땡겨와 작업 시행

4. 리뷰어에게 PR보낸후 작업은 어떤식으로 진행 ? PR 보낸 기준에서의 newteam-32 브랜치를 생성이후 작업하여 리뷰어께서 리뷰를 마치면 team-32 브랜치 rebase 설정 이후에 team-32 브랜치에 newteam-32 브랜치에서 작업한 내용들을 merge 해줌으로써 따로 공백기간 없이 개발 진행함

> 최대한 깃 충돌이 나지 않게 하기 위하여 서로 폴더 단위로 작업 진행 예정

---

### 그라운드룰

1. 작업을 하다가 서로 헷갈리거나 모르는 부분 질문 & 해결

2. 마무리 스크럼때 서로 작업한 내용을 간단히 공유

   > 각자 맡은 기능에 대하여 어떻게 접근했고 어떤식으로 해결했는지 ? (README, Notion)

3. esLint, prettier를 사용하여 코드 일관성 맞추기

4. 저녁 8시 ~ 이후에 줌에서 미션 진행 (늦어도 9시까지 접속하기)

5. 각자 작성한 코드를 충분히 이해하고 넘어가기

---

### 커밋 템플릿, 이슈 템플릿

1. 커밋 템플릿

```
################
# feat : 새로운 기능 추가
# fix : 버그 수정
# docs : 문서 수정
# test : 테스트 코드 추가
# refact : 코드 리팩토링
# style : 코드 의미에 영향을 주지 않는 변경사항
# chore : 빌드 부분 혹은 패키지 매니저 수정사항
################

Example) feat: ~~~~기능 작업 진행 중
         description: todo add ~~식으로 기능 작업
```

2. 이슈 템플릿 -> 개인적인 이슈 사항들은 템플릿이 아닌 README & Notion에 정리

---

### 회의록/이슈/위키 연결

Git Wiki에서 모두 관리 하도록!

---

### 계획

Lists

- [x] esLint & pritter 설정
- [x] babel & webpack 설정
- [x] HTML 마크업 진행
- [x] 기능 분리 논의
  - ADD ? 어떻게 할건지..
  - Remove ? 등등
- [x] TODO 데이터 논의
- [x] 폴더 분리 (어떤 폴더들이 필요할지?)

TO-DO LIST 기능 정리

- [x] TODO READ (Jinnie & Muffin)

  - [x] TODO dummy data 화면에 뿌리기
  - [x] client todos 배열 화면에 뿌리기

- [ ] TODO COUNT (Muffin)

  - [x] 맨 처음 로드시 각 Column의 개수만큼 TODO COUNT 셋팅
    - TodoColumn을 만듬과 동시에 TodoColumn이 TodoCount를 알고 있게 한다?
    - TodoColumn 생성자에서 TodoInput을 만들때, onRegisterBtn에 대한 콜백함수를 넘겨줘야할까 ?
    - 그 이전에, TodoColumn 클래스는 TodoColumn만 관리하는 데이터만 있어야할텐데. TodoInput을 알아도 될까?
  - [x] 투두 카드 등록 완료 후 해당 Column에 맞는 Count 증가
    - TodoColumn 클래스에서 할지? TodoCount 클래스 만들어서 할지 고민 => TodoColumn 클래스 내에서 count 변수로 관리
    - count data를 column 마다 계속 가지고 있어서 카드 등록 시 count + 1 해주기
  - [ ] 투두 카드 삭제 시 해당 todoColumn에 맞는 count 1 감소
    - 투두 카드 삭제 구현 완료 시 handleCount 콜백함수만 넣어주면됨.

- [x] TODO CREATE (Jinnie & Muffin)

  - [x] 버튼 마우스 오버시 색상 변경
  - [x] 버튼 클릭 시 카드 생성
  - [x] 카드가 렌더링된 상태인데 + 버튼 한 번더 클릭시 카드 제거
  - [x] 취소 버튼 클릭시 카드 제거
  - [x] 내용이 입력될시만 등록버튼 활성화 + 내용없을 시 등록버튼 비활성화
    - input addeventListener 에서 `value 값` 유무에 따라 활성화 여부 체크
    - 버튼속성에 `disable 속성` 추가하면 비활성화 버튼으로 됨
  - [x] 내용 입력 후 등록시 카드가 등록되고, 해당 카드 제거
    - 전체 todo를 관리하는 todos 배열에 카드가 등록
    - 🤔 시간 데이터 추가 필요
    - 서버 반영은 차후에...
    - ~해당 카드 remove.~
  - [x] 글자수 제한 500자 이내
  - [x] 글의 길이에 맞춰 박스가 아래로 늘어나도록 구현 + input height값도 함께 늘어난다.
    - ~div태그로 input태그처럼 구현? input(hidden 속성)을 사용 -> value만 관리하는 녀석, div가 view를 관리하는 녀석~
    - textarea 속성을 사용하여 해결

- [ ] TODO UPDATE (Jinnie)

  - [x] 카드 더블클릭시 수정이 가능한 상태로 변경
    - dbclick addeventlistener 에서 수정 변경 가능하도록 구현
    - ~state 값을 두어서 현재 카드가 수정상태인지 판별~
  - [x] 취소 버튼 클릭시 수정 취소. 변경사항 반영 X
    - ~수정 여부의 데이터 state값을 false로 하여 카드 원복~
  - [ ] 내용을 모두 삭제시 수정 버튼 비활성화, 내용을 수정한 후 수정버튼 클릭시 변경사항 반영
    - content 내용이 없을 시 버튼 비활성화

- [ ] TODO DELETE (Jinnie)

  - [ ] 칼럼 X 버튼은 마우스 오버시에만 색변경, 클릭은 아무 이벤트 X
  - [ ] 카드 X 버튼 마우스 오버시 카드색 변경
  - [ ] 카드 X 버튼 삭제 팝업창이 띄워지도록 구현
  - [ ] 알럿창 취소 버튼 클릭시 삭제 팝업창 닫힘
  - [ ] 알럿창 삭제 버튼 클릭시 해당 카드 삭제 + 삭제 팝업창 닫힘

- [ ] MENU 버튼 클릭 시 화면 노출 (Muffin)

  - [x] 애니메이션 효과 추가
  - [x] X 버튼 클릭 시 MENU 화면 없어짐
  - [x] 사용자 액션 최신순으로 보여주기
  - [x] 기록이 많아지면 스크롤 생성
  - [x] 이동, 추가, 수정, 삭제시 Alarm Class가 알 수 있도록 TodoNotice 클래스 구현
  - [x] title, content, status 값은 bold 처리해서 렌더링
  - [x] 모든 화면 크기에서 동일하게 동작되도록 코드 수정
  - [ ] 코드 리팩토링

- [ ] TODO DRAG & DROP (Muffin)

  - [ ] 위 아래로 같은 칼럼내, 다른 컬럼으로도 이동 가능
  - [ ] 드래그앤드랍 시 원래 카드 잔상이 생김
  - [ ] 드래그 중단시 카드는 잔상이 있던 위치로 이동 (잔상은 사라짐)

- [ ] 칼럼 타이틀 더블 클릭 시 수정 가능하도록 변경
- [ ] 우측 하단 FAB 버튼 클릭 시 컬럼 CRUD

---
