
# TO-LIST (BE)

---

## 도메인

<details>
<summary>접기/펼치기 버튼</summary>
<div markdown="1">

### Work 도메인
```java
private Long id; // workId
private String title; // 제목
private String content; // 본문
private User author; // 작성자
private WorkStatus workStatus; // 상태(column?) 
private Integer statusIndex; // 상태 안에서의 순서
private LocalDateTime createDateTime; // 생성시간
private LocalDateTime lastModifiedDateTime; // 최근 수정시간
private boolean isDeleted; // 삭제됐는지 여부
```
- Work를 정의한 도메인

### User 도메인
```java
private Long id; // 사용자Id
private String name; // 사용자 이름
```
- 로그인 기능을 구현하지 말라는 요구사항이 있었음.
- Repository에 사용자 한 명을 저장해뒀고, 이 사용자가 로그인한 상태에서 Work에 관한 여러가지 요청을 하는 시나리오를 생각함

### History Domain
```java
private Long id; // History Id
private Work work; // history의 대상이 되는 work
private HistoryType historyType; // 어떤 history인가 (추가, 내용수정, 이동, 삭제)
private LocalDateTime historyTime; // 시각
private WorkStatus beforeStatus; // 이전 상태
private WorkStatus currentStatus; // 현재 상태
```
- 모든 추가, 내용수정, 이동, 삭제 등에 대한 기록을 정의한 History 도메인

</div>
</details>

---

## 컨트롤러 및 기능 요구사항

### WorkController
#### 1. workSave (work 추가)
   - work를 추가했을 때 `TODO`로 저장되어야하고, 제일 TODO의 마지막 순서에 저장되어야함.
   - 추가 내역을 History에 기록해야함

#### 2. workUpdate (work 내용 수정)
   - work의 내용을 수정함
   - 수정 내역을 History에 기록해야함

#### 3. moveWork (work를 다른 status로 이동)
   - 같은 status, 같은 순서로 이동 요청 시 아무런 작업을 수행하지 않고 응답
   - 같은 status, 다른 순서로 이동 요청 시 해당 순서의 work와 순서를 변경
   - 다른 비어있는 status 0번으로 이동 요청 시 0번 순서를 부여해서 이동
   - 다른 비어있지 않은 status로 이동시 해당 위치에 삽입후 뒤의 순서를 뒤로 밀어냄
   - 일련의 과정에 영향을 받은 모든 Work들에 대하여 DB측에도 실제 순서변경 내역을 반영
   - 이동 내역을 History에 기록

#### 4. workDelete (work 삭제)
   - work의 index 값 제거, work의 status 제거
   - work의 상태를 isDeleted로  변경
   - work의 삭제 시 다른 work들의 순서변경을 DB에 반영
   - 삭제 내역을 History에 기록

#### 5. workAllList (사용자의 모든 work 반환)
   - 사용자의 모든 work를 반환해야함
   - 이때 deleted 상태가 true인 work들은 제외해야함

### HistoryController
#### 1. allHistory (사용자의 모든 History 반환)
  - 사용자의 모든 History를 반환
  
---

## 로치 님의 피드백

- [x] moveWorkInColumn(Work work, Integer order)
  - 같은 순서로 요청이 왔을 때는???
    - 같은 순서로 요청했을 때는 DB에 아무런 쿼리를 날리지 않아도 되도록 함

- [x] moveWork(Long id, WorkMoveRequest requestDto)
  - 이와 같이 서비스 메서드의 매개변수로 Dto를 바로 쓰는 것 대신, 컨트롤러에서 값들을 가져오고 인자를 넘기는 방식은?
    - 의견을 반영해서 사용해봤는데 불편하다는 느낌도 약간 있었다. 물론 기존대로 requestDto를 바로 넘겼다면 더 간단해졌을지 지는 실제로 구현해봐야 알 것 같긴 하다.
  
- [ ] ColumnService의 moveWork 메서드의 내부 코드 복잡도가 너무 높음.
  - 메서드를 더 쪼갤 필요가 있다!
    - 이를 개선해보려고 시도를 계속 해봤지만 내부적인 분기처리를 많이하다보니 코드 복잡도가 늘어난 감이 있다.

- [x] 페이지 테이블?
  - 사용자와 페이지 테이블은 1:1 관계에 있음
  - 사실상 거의 동일한 느낌이라서... 굳이 또 정의를 할 필요가 있는가?
    - 피드백을 반영하고, Page를 사용하지 않음. 더 나아가, Column을 사용하지 않고 바로 Work 내에서 인덱스를 가지고 있도록 함

---

## 앞으로 해야하는 것
- [ ] DB 연동
- [ ] 배포
- [ ] 테스트코드... (시간 상 힘들 것 같긴 함...)

---
