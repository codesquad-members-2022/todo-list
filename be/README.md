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
| GET | /list    | ToDo-List 전체를 json으로 내려줌   |
| POST | /list  |  등록  |
| DELETE  | /list/{id}  |  삭제  |
| POST | /list/{id}   |  변경  |
| GET | /list/menu   |  활동 로그  |


<br>

### API 설계

| status | 값 |
| -- | -- |
| todo | 1 |
| progress | 2 |
| done | 3 |

<br>

POST /list 일 때의 API

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

POST, DELETE /list/{id} 와 response의 API

```
{
    "id": 1,
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
| move | 1 |
| post | 2 |
| delete | 3 |

<br>

GET /list/menu 요청 시 서버에서 iOS로 내려주는 json 형식

```
// progress에 있던 카드 done으로 이동 시 response body json
[
    {
        "title": "제목",
        "type": 1,      //이동
        "previous": 2,  // progress 에서
        "status": 3     // done 으로
    },
// todo에 카드 등록 시 response body json
    {
        "title": "제목1",
        "type": 2,      //등록
        "previous": null
        "status": 1     //todo
    },

// todo에 있던 카드 삭제 시 response body json
    {
        "title": "제목2",
        "type": 3,      //삭제
        "previous": 1   //todo
        "status": null
    }
]
```
