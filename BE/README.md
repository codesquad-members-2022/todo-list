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
