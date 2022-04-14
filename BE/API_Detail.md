
# BE - API 명세

## Work
### 1. work 추가
1. 요청
  - Path : `/works`
  - Method : `Post`
  - 필요 인자
    - title : 제목 (문자열)
    - content : 본문 (문자열)
    - (요청 바인딩 객체 : `codesquad.web.works.dto.WorkSaveRequest`)


2. 응답 : `codesquad.web.works.dto.WorkSaveResponse`
   - workId : 저장 후 서버측에서의 해당 Work를 식별하기 위한 ID값(Long)
   - (응답 바인딩 객체 : )
   

### 2. 회원의 모든 Work 가져오기
- 요청
    - Path : `/works`
    - Method : `Get`
    - 필요 인자 : 필요 없음
- 응답 : `codesquad.web.works.dto.WorkListResponse`
    - `List<WorkDetaialResponse> works`를 가지고 있음
      - id : work의 id값(Long)
      - author : 작성자
      - title : 제목
      - content : 본문
      - workStatus : 상태(TODO:할일, PROGRESS:진행중, DONE:끝)
      - statusIndex : 상태 내에서 순서(0,1,2,3,...)
      - createDateTime : 생성일자
      - lastModifiedDateTime : 마지막 수정일자
      

### 3. 회원의 Work 내용 수정하기
1. 요청
  - Path : `/works/{id}`
  - Method : `Put`
  - 필요 인자 : id, `codesquad.web.works.dto.WorkUpdateRequest`
    - id : path로 넘길 값. work의 식별자 id값
    - title : 수정할 제목
    - content : 수정할 본문내용

2. 응답 : `codesquad.web.works.dto.WorkUpdateResponse`
   - title : 변경 후 제목
   - content : 변경 후 본문 내용
   - lastModifiedDateTime : 수정 시각
   
### 4. Work 이동하기
1. 요청
   - Path : `/works/{id}`
   - Method : `Patch`
   - 필요 인자 : id, `codesquad.web.works.dto.WorkMoveRequest`
     - id : path로 넘길 값. work의 식별자 id값
     - targetStatus : 옮기고자 하는 status(TODO, PROGRESS, DONE). 같은 status 내에서 옮길 경우에도 현재 status를 지정해야함
     - targetStatusIndex : 옮기고자하는 인덱스(위에서부터 0,1,2,3,4, ...)

2. 응답 : `codesquad.web.works.dto.WorkMoveResponse`
   - order : 변경 후 순서값. 현재 order로 쓰고 있긴한데... 이 변수명이 마음에 안 들어서 추후 변경 예정
   - status : 변경 후 상태(TODO, PROGRESS, DONE)

### 5. Work 삭제하기
1. 요청
   - Path : `/works/{id}`
   - Method : `Delete`
   - 필요 인자 : id
     - id : path로 넘길 값. work의 식별자 id값
    
2. 응답 : `codesquad.web.works.dto.WorkDeleteResponse`
   - isDeleted : 삭제되었는 지 여부를 반환. 삭제되었으면 true
   - id : 삭제된 work의 id

---

## History
### History 전체 요청
1. 요청
   - Path : `/histories`
   - Method : `Get`
   - 필요 인자 : 없음

2. 응답 : `codesquad.web.history.dto.HistoryListResponse`
   - `List<HistoryDetailResponse> histories`를 가지고 있음 
     - author : 작성자
     - title : 제목
     - historyType : 내역 - CREATED(생성됨), MOVED(다른 column으로 옮겨짐), MODIFIED(내용이 수정됨), DELETED(삭제됨)
     - historyTime : 시각(DateTime)
     - beforeStatus : 이전 상태(TODO, PROGRESS, DONE)
     - afterStatus : 이후 상태(TODO,PROGRESS, DONE)


