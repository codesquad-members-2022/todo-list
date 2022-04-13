# BE ToDo List

### 요구사항

1. ToDo-List 등록 (insert)
2. ToDo-List 삭제 (delete)
3. ToDo-List 변경 (update)
    - 동일 Column 에서 순서 변경
    - 다른 Column 으로 이동
    - title or content 내용 변경
4. Activity Log

<br>

### URL 설계

|http Method|URL| 역할 |
| -- | -- | -- |
| GET | /list    | 조회 |
| POST | /list  |  등록  |
| DELETE  | /list/{id}  |  삭제  |
| POST | /list/vertical/{id}   | 상 하 드래그 앤 드랍 |
| POST | /list/horizon/{id}   | 좌 우 드래그 앤 드랍 |
| PATCH | /list/{id} | 제목, 내용 변경 |
| GET | /list/menu   |  활동 로그  |

<br>

### API 설계

| status | 값 |
| -- | -- |
| todo | 1 |
| progress | 2 |
| done | 3 |

<br>

POST /list,

DELETE /list/{id} 

PATCH /list/{id} 에서의 API

```
{
    "userId": "iOS",
    "title": "제목1",
    "content": "내용1",
    "row": 2,
    "status": 1  //todo 에 있는 상태
}
```

<br>

위의 요청을 제외한 모든 요청 API 방식

```
{
    "id": 1,   // 요청할 카드의 고유한 pk값 (카드의 등록순서와 동일)
    "userId": "iOS",
    "title": "제목1",
    "content": "내용1",
    "row": 2,
    "status": 1  //todo 에 있는 상태
}
```

<br>

활동 로그

| type | 값 |
| -- | -- |
| add | 1 |
| remove | 2 |
| update | 3 |
| move | 4 |

<br>

GET /list/menu 요청 시(활동 로그 요청) 서버에서 iOS로 내려주는 json 형식

```
// progress에 있던 카드 done으로 이동 시 response body json
[
    {
        "title": "제목",
        "type": 4,      // 이동
        "previous": 2,  // progress 에서
        "status": 3,     // done 으로
        "time": "2022-04-05T12:15:00.000+09:00"
    },
// todo에 카드 등록 시 response body json
    {
        "title": "제목1",
        "type": 1,      //등록
        "previous": null,
        "status": 1,     //todo
        "time": "2022-04-05T12:15:00.000+09:00"
    },
// progress에 있던 카드 삭제 시 response body json
    {
        "title": "제목2",
        "type": 2,       //삭제
        "previous": 2,   //progress
        "status": null,
        "time": "2022-04-05T12:15:00.000+09:00"
    }
]
```
