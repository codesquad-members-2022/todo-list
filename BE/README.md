## 흐름
유저 -(할일 저장해주세요)-> Work -(사용자 입력)-> dto -> controller -> Service -> DB

## 기능
- 보여주가(get)
- 등록(post)
- 수정(post)
- 삭제(delete)
- 이동(patch)

## Work Domain
- [x] Work
  - PK(id)
  - title
  - content
  - 작성자
  - 상태 Enum으로 관리
  - 작성 날짜
  - 마지막 수정 날짜
- [x] Repository 만들기 (memory)
  - [x] test 코드 작성하기

## Column
- [x] Column
  - [x] id (PK)
  - [ ] author (추후 진행)
  - [x] works

## Controller
- [x] 할 일 추가 컨트롤러 생성
- [x] Work 수정 컨트롤러 만들기
- [x] Service 계층 분리하기
- [x] Work 목록 API 응답 기능

## 흐름
- Work 생성 요청(`/works`, `post`) dto(title, content, 작성자)
- Work 내용 수정 요청(`/works/{id}`, `put`)
  - id(pathVariable), dto(title, content, 작성자)
- Work 이동 요청(`/works/{id}`, `patch`)
  - id(pathVariable), dto(status, order(status별 순서값?))
  - pathVariable로 id에 해당하는 Work Entity 찾아오기
  - status 수정
    - 같은 status, 순서 변경
      - 예> 1번을 3번으로 순서변경 -> 2번의 순서가 1이 되고 3번의 순서가 2가 되고 1번의 순서는 3이 되는게 같은 트랜잭션에서 진행되어야함.
    - 다른 status, 특정 순서 삽입
      - 예> TODO의 2번에 있던 것을 PROGRESS의 5번에 삽입
        - TODO의 3,4,5,6,7,... 의 순서값을를 모두 2,3,4,5,6,... 으로 수정하고 DB에 반영해야함...
        - PROGRESS의 5,6,7,8,...의 순서를 모두 6,7,8,9,...으로 수정 후 4번으로 순서를 변경하고 dB에 반영해야함...
- 삭제 요청 delete -> id(pathVariable) -> 삭제
  - TODO의 5번에 있던 것을 삭제
  - 6,7,8,9,...에 있던 것들의 순서를 앞으로 땡겨와야함

---

# 로치 님의 피드백

- [ ] moveWorkInColumn(Work work, Integer order)
  - 같은 순서로 요청이 왔을 때는???
  - 아이디어 :

- [ ] moveWork(Long id, WorkMoveRequest requestDto)
  - 이와 같이 서비스 메서드의 매개변수로 Dto를 바로 쓰는 것 대신, 컨트롤러에서 값들을 가져오고 인자를 넘기는 방식은?
  
- [ ] ColumnService의 moveWork 메서드의 내부 코드 복잡도가 너무 높음.
  - 메서드를 더 쪼갤 필요가 있다!

- [ ] 페이지 테이블?
  - 사용자와 페이지 테이블은 1:1 관계에 있음
  - 사실상 거의 동일한 느낌이라서... 굳이 또 정의를 할 필요가 있는가?

---

## 스토리

- 사용자 로그인은 고려하지 않음. 단 한명의 사용자가 존재함.
- 최초 화면에 띄울 때 목록이 보여야함
  - 목록을 보일 때는 API 요청 3개가 날아온다.
    - 상태가 TODO인 Work의 목록(사용자의) "/works?workstatus=todo" ?
    - 상태가 PROGRESS인 Work의 목록(사용자의)
    - 상태가 DONE인 Work의 목록(사용자의)
- post ("/works") 요청시 '일'이 추가됨
  - todo의 제일 마지막에 저장되어야함
  - 서버에서 사용자의 todo에 해당하는 Work들을 전부 조회하여, 제일 마지막 순서 + 1의 순서값을 부여해야함
- get("/works/{id}") 요청시 '일'의 상세 내역을 반환함
- put("/works/{id}") 요청시 '일'의 글, 본문을 변경함
- patch("works/{id}") 요청시 해당 status 내에서의 순서를 변경하거나, 다른 status의 특정 순서로 변경
- delete("/works/{id}") 요청시 '일'이 제거됨
  - 단순히 삭제로만 끝나는 것이 아님.
  - 이 때, 현재 존재하는 같은 status인 work의 내부 순서값도 쫘르륵 변경됨
