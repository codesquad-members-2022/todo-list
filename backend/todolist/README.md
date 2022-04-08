# TODO 리스트 서비스


[swagger 실행 URL](http://localhost:8080/swagger-ui/index.html)

### Api 정의서

| URL                | 기능      |
|--------------------|---------|
| POST /api/todo     | 새 카드 등록 |


### 요구사항 및 설계

<details markdown="1">
<summary>카드 생성하기</summary>

#### 요구사항

- 사용자는 카드를 등록한다.
    - 카드 등록시에는 `해야할 일/하고있는 일/완료한 일` 중 하나를 선택하여, 제목과 내용을 입력한다.



#### 비즈니스 로직

- **POST /api/todo** 를 통해 요청 받는다
- 사용자 id, 제목, 내용을 전달 받는다.
- `검증` 각각의 값에 대한 null, 공백여부를 검증한다.
- `검증` 제목은 50글자 이하여야 한다.
- `검증` 내용은 500글자 이하여야 한다.
- 전달받은 데이터 검증 처리 후 todo_list_table DB 저장을 실행한다.
- 동일 작업을 todo_user_history_table 에도 등록 기록을 저장한다.
- PRG 패턴 결과 응답코드 : 200
  - POST 이후 GET /{user-id}/card/{id} 로 요청 
  - 쓰기 요청 결과 redirect 통한 조회로직에는 사용자와 카드 정보 조회를 통해 데이터 무결성 확인 할 수 있다고 생각


#### 비기능



</details>



#### BE todolist
- 요구사항 분석
- DB 설계 및 android 팀과 미팅
- 요구사항 분석 및 설계








