# TODO 리스트 서비스


[swagger 실행 URL](http://localhost:8080/swagger-ui/index.html)

### Api 정의서

| URL            | 기능                              |
|----------------|---------------------------------|
| GET /api/todo  | `해야할 일/하고있는 일/완료한 일` 별 카드 목록 조회 |
| POST /api/todo | 새 카드 등록                         |

<br>

### DB(todo_list_table)와 서버(Card)의 분리

| server (Card)  | DB (todo_list_table) |
|----------------|----------------------|
| cardId         | todo_id              |
| subject        | subject              |
| content        | content              |
| status         | todo_status          |
| order          | todo_order           |
| deleted        | writing_date         |
| createdAt      | deleted              |
| userId         | todo_user_id         |


<br>

#### code rules

- DB 조회 결과 받은 도메인 객체에는 -info 접미어를 붙입니다.
``` java
  Card card = request.toEntity();
  Card cardInfo = cardDao.save(card);
```


### 요구사항 및 설계

<details markdown="1">
<summary>카드 목록 조회</summary>

#### 요구사항

- 사용자는 애플리케이션 접속시, 첫 화면을 통해 카드 조회 요청을 한다.
- `해야할 일/하고있는 일/완료한 일` 별로 각각의 카드 목록들이 나열된다.
- `해야할 일/하고있는 일/완료한 일` 별 카드 목록 순서를 갖고 나열된다.
  - 카드 등록시에는 가장 위에 놓여진다.
  - 사용자는 카드를 위/아래로 이동시키며 카드 순서를 변경할 수 있다.


#### 비즈니스 로직

- **GET /api/todo**
- 요청 헤더를 통해 userId를 받는다.
  - 키는 "user" 로 값은 1을 가정한다.
  - 응답은 data 안에 각각의 status 별로 카드목록이 리스트로 담긴다.
- DB 조회
  - todo_status 는`todo, ongoing, completed` 3가지 속성값을 가진다.
  - user_id에 해당하는 삭제되지 않은 상태의 각 todo_status 별로 todo_order 역순 정렬 조회한다.
- Service layer
  - @Transactional(readOnly = true)
  - CardDto.WriteResponse -> CardDto.CardResponse로 변경
    - 각각의 Card 정보를 CardDto.CardResponse로 담아서
    - 각 status 별 data에 담아 반환한다.

#### 비기능


</details>



<details markdown="2">
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


---


#### BE todolist
- 요구사항 분석
- DB 설계 및 android 팀과 미팅
- 요구사항 분석 및 설계
- ec2 배포
- 카드 등록 기능 구현



<details markdown="1">
<summary>📑 from reviewer </summary>

#### 참고

- [DB 저장시 네이밍과 enum](https://techblog.woowahan.com/2527/)
- [네이밍 - 클린코드](https://velog.io/@dnr6054/Clean-Code-2-%EC%9D%98%EB%AF%B8-%EC%9E%88%EB%8A%94-%EC%9D%B4%EB%A6%84)


#### 내용 정리


- URL convention != Rest Api 목록
- Dto
  - inner class와 @Data
- @RequestMapping : 클래스 레벨 이용
- @PostMapping
- ResponseEntity 사용의도와 구조
- lombok을 좀 더 보수적으로 사용해봅시다.
  > AllArgsConstructor, Builder 가 둘다 존재해야 될까요?
  > 접근 제한자를 두지 않아도 될까요?
  > 
  > builder는 매우 편리하지만 nullsafe하지 못한 위험성이 있기에 보수적으로 사용해야 합니다.
  이 점을 알고 계신다면 builder를 어느 경우에 쓰면 되겠다. 라는 생각이 정리 되실거 같아요.
  생성자, 빌더, 정적 팩토리 메서드를 공부해보시기를 추천합니다.

- ResutSet의 LocalDateTime
  ``` java
  rs.getTimestamp(CARD_WRITING_DATE).toLocalDateTime()
  ```
- 들여쓰기를 일관성있게 해주는 것이 가독성에 좋을 것 같습니다.


  > 이 코드를 따라가기 위해서는 from의 반환형을 알아야합니다.
  물론 ide에서는 추적이 쉬워 알 수 있지만, pr에서는 알기 쉽지 않습니다.
  즉 가독성을 위해서는 TodoStatus를 붙여서 TodoStatus.from() 으로 하면 좋다고 생각합니다.
  상수명으로 TODO_STATUS도 언급되어서 라고 말하셨는데, 매개변수에서 반환형을 추론하는 것은 일반적이지 않다고 생각합니다.

- card와 todo를 구분하려는 시도는 좋습니다.


- Dao에서 컬럼의 순서를 가지고 쿼리보다는 컬럼이름으로 하도록 한다.
  - Deleted time 으로 보이는데 DELETED boolean과는 다른 값으로 보입니다.
  ``` java
  public static final int COLUMN_INDEX_DELETED = 5;
  ```

### 2nd

[1주차 3th PR](https://github.com/codesquad-members-2022/todo-list/pull/126)



</details>
