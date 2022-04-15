# TODO 리스트 서비스


[swagger 실행 URL](http://localhost:8080/swagger-ui/index.html)
[ERD](https://www.erdcloud.com/d/MR5jNfSsokwxpxJ3Z)


### Api 정의서

| URL                            | 기능                              |
|--------------------------------|---------------------------------|
| GET /api/todo                  | `해야할 일/하고있는 일/완료한 일` 별 카드 목록 조회 |
| POST /api/todo                 | 새 카드 등록                         |
| GET /api/todo/card/{id}        | 카드 상세 조회                        |
| PATCH /api/todo/card/{id}      | 카드 수정 (제목/내용)                   |
| DELETE /api/todo/card/{id}     | 카드 삭제 하기                       |
| PATCH /api/todo/card/{id}/move | 카드 이동 하기                    |
| GET /api/todo/history          | 나의 활동 기록 List 반환         |

<br>

### DB(todo_list_table)와 서버(Card)

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

- 작은단위 분업시, 충돌을 최소화하기 위해서 Controller method 위치 순서에 따라 CardService의 메서드 위치에서 작업합니다.
  - 분리되어지는 private 메서드들은 해당 public 메서드 하위에 위치 시켜 충돌시에도 묶음으로 처리할 수 있게 합니다.
- CardDto, CardDao에 추가되어지는 메서드들은 가장 하단에 추가하도록 합니다.
  - 시작시에 Controller에서 연결되어지는 메서드 명을 주석으로 붙여 알아볼 수 있게 합니다.
  - pr 전 주석 제거 합니다.
- 충돌에 대비한 위치 구분 위한 주석 외에, 로직 설명을 위한 주석은 javadocs로 추가합니다.
  - 간결하게 설명, 핵심 정보를 담도록 노력합니다.
- CardDto 내 클래스 추가는 static inner class 로 thread-safe 하게 작업합니다.
- 메서드의 경우 파라미터 개수에 따라 -from/-of 접두어를 붙일 수 있습니다.
- Naming
  - 구분되는 상수영역간에는 개행을 추가하여 가독성을 높이고자 합니다.
    - 상수명은 해당 도메인 명을 `Card-` 접미어로 붙여, 다른 도메인 영역에서 구분되어 사용될 수 있게 합니다.
    - 에러 상수명의 경우 `ERROR_OF-` 접미어를 붙여줍니다.
- 테스트 작성
  - 테스트 영어 메서드명은 간단하기 주요 정보만 표시하고, @Displayname에 BDD 방식으로 작성합니다.


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
- 동일 작업을 todo_history_table 에도 등록 기록을 저장한다.
- PRG 패턴 결과 응답코드 : 200
  - POST 이후 GET /{user-id}/card/{id} 로 요청 
  - 쓰기 요청 결과 redirect 통한 조회로직에는 사용자와 카드 정보 조회를 통해 데이터 무결성 확인 할 수 있다고 생각


#### 비기능



</details>


<details>
<summary>카드 이동하기</summary>

#### 요구사항

- 사용자는 임의로 카드의 위치 (칼럼, 칼럼 내 정렬 순서) 를 변경할 수 있다.
  - 사용자는 한 번에 하나의 카드를 선택하여 특정 위치로 이동을 시도할 수 있다. 
  
#### 비즈니스 로직

- **PATCH /api/todo/card/{id}** 경로를 통해 요청 받는다.
- path variable로 카드 id, body로 이동 목표 (칼럼 이름, 칼럼 내 정렬 순서)를 전달 받는다.
- 카드의 현재 위치와 목표 위치가 같을 경우 아무 동작도 하지 않는다.
- todo_list_table에 저장된 카드들의 정렬 순서(order)를 다음과 같은 순서로 업데이트한다. 
- 같은 칼럼 내에서 이동이 발생할 경우:
  - 높은 순서에서 낮은 순서로 이동하면 원래 위치와 목표 위치 사이의 카드(목표 위치에 원래 있었던 카드 포함)들의 정렬 순서를 1씩 증가시킨다. 
  - 낮은 순서에서 높은 순서로 이동하면 원래 위치와 목표 위치 사이의 카드(목표 위치에 원래 있었던 카드 포함)들의 정렬 순서를 1씩 감소시킨다.
- 다른 칼럼으로 이동할 경우:
  - 목표 위치보다 높은 순서의 카드(목표 위치에 원래 있었던 카드 포함)들의 정렬 순서를 1씩 증가시킨다.
- 이동하는 카드의 todo_status와 order를 목표 위치의 값으로 업데이트한다. 
- 다른 칼럼으로 이동하였을 경우 todo_history_table 에 이동 기록을 저장한다. 

</details>

<details>
<summary>활동 내역 조회</summary>

#### 요구사항

- 사용자는 자신의 카드 등록, 삭제, 변경, 이동 내역을 조회할 수 있다.

#### 비즈니스 로직

- **GET /api/todo/history** 경로를 통해 요청 받는다.
- 커스텀 HTTP 헤더 `user` 를 통해 사용자 ID를 전달 받는다.
- todo_history_table에서 사용자 ID가 일치하는 레코드를 조회하여 응답으로 전송한다.

</details>

---


#### BE todolist
- 1st Week
  - 요구사항 분석
  - DB 설계 및 android 팀과 미팅
  - 요구사항 분석 및 설계
  - ec2 배포
  - 카드 등록 기능 구현
- 2nd Week
  - 카드 목록 조회 기능 구현
  - 카드 이동 요청 기능 구현
  - 카드 수정/삭제 기능 구현
  - 카드 활동 기록 기능 구현 
    

<details markdown="1">
<summary>📑 from reviewer </summary>


#### 참고

- [DB 저장시 네이밍과 enum](https://techblog.woowahan.com/2527/)
- [네이밍 - 클린코드](https://velog.io/@dnr6054/Clean-Code-2-%EC%9D%98%EB%AF%B8-%EC%9E%88%EB%8A%94-%EC%9D%B4%EB%A6%84)


#### 내용 정리

`nit` : you don't have to fix these points, but we'd like you to

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

``` java
    public CardDto.WriteResponse readOf(Long id) {
      String errorMessage = String.format(ERROR_OF_CARD_ID, id);
      Card cardInfo = cardDao.findById(id)
          .orElseThrow(() -> new IllegalArgumentException(errorMessage));
      return new CardDto.WriteResponse(cardInfo);
  }
```


> `nit`
> - 이것은 아마 lambda의 function을 이용하면 좀 더 용이하게 바꿔줄 수 있을 것 같아요.
> - 개선은 하셨지만 여전히 errorMessage가 스택에 쌓이고 있네요.
>  지금은 이 부분이 문제가 있다는 것을 인지하시고 차후에 개선하셔도 좋습니다.
>  <br>
> 이런 것을 나아가 customException으로 분리할 수 있습니다.


### 3rd


[2주차 1st PR](https://github.com/codesquad-members-2022/todo-list/pull/128#discussion_r847960615)


- PATCH /api/todo/{id} -> /api/todo/card/{id}
- GET /api/todo 는 api 해석상 조회아닌가요?
  - 이후 welcomePage로 가는지 여부는 `백엔드의 관심사`가 아닌 것 같습니다.
- 메서드 컨벤션상 public을 상단에 올리시는 것을 추천합니다.
- **패키징을 통해 dto의 사용 레이어를 구분해보면 어떨까요?**
  흐름을 볼 떄 request, response는 controller 영역에서 쓰이는 dto들인 것 같습니다.
  최소한 controller에서 쓰이는 dto, service에서 쓰이는 dto 정도는 분리가 되어야 할 것 같습니다.
- forEach() -> map() or collect()
  ``` java
      @Data
      public static class CardsResponse {
          private final Map<String, StatusResponse> data;
  
          public CardsResponse(List<CardByStatus> cards) {
              this.data = cards.stream()
                  .collect(Collectors.toMap(
                      key -> key.getStatus().getText(),
                      val -> new StatusResponse(val.getCount(), val.toResponse())
                  ));
          }
      }
  ```
  > CardsResponse가 맞을까요? CardResponses가 맞을까요? <br>
    지금의 내부를 보면 Card정보가 아닌 status정보만 있습니다.  <br>
    그렇다면 CardStatusResponses가 맞을까요?  <br>
    status만 내려주는 것이 업무 요건에 적절한가요?  <br>


- to라는 접두어는 A객체가 B객체로 변한다는 의미로 A객체의 메서드로서 존재하는 것이 직관적입니다.
  지금의 로직은 mapper와 같은 단어가 더 적합해 보입니다.

  
</details>
