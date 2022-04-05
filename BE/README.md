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

## ToDo list
- [ ] 도메인 Work 객체 만들기
- [ ] Repository 만들기 (memory)
  - [ ] test 코드 작성하기

