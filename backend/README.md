# backend

### local database

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 실행

```shell
docker-compose -f docker/local-database.yml up -d
```

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 종료

```shell
docker-compose -f docker/local-database.yml down
```

** 배포 시 EC2 인스턴스 내에서 도커를 이용해 데이터베이스 서버를 실행하지 않고,  
인스턴스 내에 직접 데이터베이스를 설치합니다.

# team-17 BACKEND

## ERD

![image](https://user-images.githubusercontent.com/92678400/163530581-3abf3f2b-39e1-4592-99b4-720766830ab3.png)

## API 명세

https://github.com/rxdcxdrnine/todo-list/wiki/REST-API-%EB%AA%85%EC%84%B8

* API Endpoint

`GET /columns`  
`GET /histories`

`POST /cards`  
`PUT /cards/:id/move`  
`PATCH /cards/:id `  
`DELETE /cards/:id`

![image](https://user-images.githubusercontent.com/92678400/163530316-6ba3b9ab-fc9d-4e55-bd93-05438dea60ae.png)

![image](https://user-images.githubusercontent.com/92678400/163530342-47f9d91c-70d5-48bb-bafc-48fef3a82ffb.png)

![image](https://user-images.githubusercontent.com/92678400/163530368-a846b51a-f2c6-46ec-92ac-d5361ca9d37f.png)

![image](https://user-images.githubusercontent.com/92678400/163530405-ccbc3416-5c5f-45ff-869f-ce24c883fb80.png)

## 카드의 정렬 방법

cardId와 별개로 카드의 순서를 나타내는 필드가 있는 것은 동일하나, 어떤 방식으로 순서를 적용해야 가장 효율적일지 고민 하였습니다

### Option 1 : 순차적으로 정렬

<img width="173" alt="스크린샷 2022-04-15 13 47 18" src="https://user-images.githubusercontent.com/92678400/163526378-c9bbd1e7-39cb-4987-8ca5-7173a026a68f.png">

* 정렬 방법: 컬럼의 상단에 큰 번호가 오도록 오름차순으로 카드 순서가 할당된다


* 생성 : 해당 컬럼에서 가장 큰 인덱스에 +1 가산된 값을 새 카드의 인덱스로 배정한다
* 이동/삭제 : 해당 카드 보다 위에 위치한 카드들의 순서를 전부 수정해줘야한다(+1 또는 -1)

* 단점 : 범위 내의 모든 카드의 필드를 수정해야하므로 카드가 많아지면 성능이 떨어진다

### Option 2 : 순차적으로 하되 이동 시 중간값에 끼워넣기

<img width="278" alt="스크린샷 2022-04-15 13 51 34" src="https://user-images.githubusercontent.com/92678400/163526388-5788f98f-46ca-4d63-bf87-a4399973b994.png">

* 정렬 방법 : 오름차순으로 하되 순서 간격을 넓게 잡는다 (ex: 10000, 20000, 30000 ...)


* 생성 : 해당 컬럼의 최신 카드 기준으로 인덱스를 +10000 가산해준다
* 이동 : 끼워넣는 위치의 두 카드의 중간 값을 인덱스로 한다. A카드, B카드 사이에 끼워넣는다고 하면 인덱스는 (A+B)/2
* 삭제 : 다른 카드들의 순서를 변경할 필요가 없다


* 장점 : 이동, 삭제 시 다른 카드들의 순서를 수정하지 않아도 된다
* 단점 : 이동 횟수가 많아져 더 이상 중간값을 정수로 나타내지 못하게 되기 전에 카드 인덱스들을 다시 10000,20000.. 형태로 변환해주는 처리를 일괄적으로 해주어야한다

### Option 3 : LinkedList 로 만들기

<img width="570" alt="스크린샷 2022-04-15 14 02 45" src="https://user-images.githubusercontent.com/92678400/163526514-ce8744c2-f245-4f8d-b494-f223f9e908d1.png">

* 정렬 방법 : 다음 카드의 id를 각 카드의 `nextId` 필드에 저장한다.  
  컬럼의 첫번째 카드(A)는 `nextId`가 `null`이 되고, 다음 카드(B)는 `nextId`가 `A`가 된다.


* 생성 : 해당 컬럼의 최신 카드의 id를 새 카드의 `nextId`로 할당한다.
* 이동 : 이전 위치의 카드, 이동하는 위치에 있는 카드, 그리고 이동되는 카드의 `nextId`를 바꿔준다
* 삭제 : 직전 카드의 `nextId`를 바꿔준다.


* 장점 : 세개의 nextId 만 바꿔주면 이동 할 수 있다.
* 단점 : 카드 생성 시 해당 컬럼의 가장 최신 카드id를 알아내려면 처음부터 끝까지 순회를 해야한다.

## 기술적인 고민

### 1. 페이징

`History` 테이블을 조회할 때 저장된 모든 레코드를 조회할 경우,  
서버-데이터베이스 및 클라이언트-서버 사이에 많은 통신 비용 발생  
-> 현재 앱 내에서 액션이 일어날 때마다 요청에 대한 응답으로 `History` 를 추가하고 있으므로,
기존의 페이징을 변형해 페이지 숫자가 아닌 현재까지 클라이언트에 저장된 `History` 레코드 배열의 길이를 요청에 포함하도록 구현

### 2. N + 1 문제

1:N 관계에 있는 `Column` 테이블과 `Card` 테이블을 조인할 때,
`Column` 테이블에서 N개의 `Column` 레코드마다 `Column` 레코드의 Id 를 FK 로 가지는 `Card` 레코드를 조회하면 N + 1 문제 발생  
-> SQL 의 `IN` 절을 이용해 N + 1 번의 쿼리 실행이 아닌 1 + 1 번의 쿼리 실행으로 수정

## 기술적인 특징

- API 문서화를 위해 프로젝트 내 Swagger 도입
- 단위 테스트 시에 `MockMvc`, `Mokcito` 기반 테스트 진행
- 통합 테스트 시에 `RestAssured`, `JdbcTest` 도입해 실제 애플리케이션 실행 시와 유사한 테스트 환경 구성

## 아쉬운 점

- `Service` 계층에 절차지향의 코드를 작성, 객체지향 설계의 부족
- 데이터베이스 내에서 인덱스에 대한 고려 부재, 대량의 데이터에 대한 테스트 부족
- `Card` 비즈니스 로직을 검증하기 위한 단위 테스트의 부재
- Json 명세, 페이징 등 실제 애플리케이션의 중요한 요소들에 대한 테스트 부족
