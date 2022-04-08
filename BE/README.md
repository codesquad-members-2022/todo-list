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

## Controller
- [x] 할 일 추가 컨트롤러 생성
- [x] Work 수정 컨트롤러 만들기
- [x] Service 계층 분리하기
- [x] Work 목록 API 응답 기능

## 흐름
- 생성 요청 post -> dto(title, content, 작성자)
- 수정 요청 put -> id(pathVariable), dto(title, content, 작성자) 
- 이동 요청 put -> id(pathVariable), dto(status) -> entity 찾아오고 -> updateStatus
- 삭제 요청 delete -> id(pathVariable) -> 삭제
