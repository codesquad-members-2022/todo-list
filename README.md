# 🗓 To do List

- To do 카드를 목록에 추가, 삭제, 수정할 수 있는 프로젝트
- 기간: 2022.04.04 ~ 2022.04.15

<br>

## 👥 Team Members

| Name | Position | Description | github                        |
| ---- | -------- | ----------- | ----------------------------- |
| Yan  | BE       | 즐겜유저    | https://github.com/KTH-96     |
| 케이 | BE       | 투머치토커  | https://github.com/leekm0310  |
| dony | FE       | FrontEnd DB | https://github.com/jindonyy   |
| 포키 | FE       | 모찌누나    | https://github.com/moonyerim2 |

<br>

## 🛠 Skills

<img src="https://img.shields.io/badge/JavaScript-F0DB4F?style=flat&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/SCSS-CC6699?style=flat&logo=Sass&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white"/>

<br>

## 🗂 Wiki

[그라운드 룰](https://github.com/KTH-96/todo-list/wiki/Ground-Rule)

<br>

## ✨ Feature

- [CRUD API](https://github.com/jindonyy/todo-list/blob/dev-FE/front/static/resources/js/utils/fetch.js)를 사용하여 To do 카드를 추가, 삭제, 수정 구현
- MVVM 아키텍처에 [Controller](https://github.com/jindonyy/todo-list/tree/dev-FE/front/static/resources/js/controllers)를 추가하여 컴포넌트가의 의존성을 낮추려 시도

<br>

## 🖥 Demo

https://user-images.githubusercontent.com/17706346/163720889-92f1f282-88c7-4929-b6b9-fbcc49d99613.mov

<br>

## 📂 Directory

```
📂 static
├── index.html
└── 📂 resources
    ├── 📂 images
    │   ├── 📂 common
    │   │   ├── icon-add-blue.png
    │   │   ├── icon-add-gray.png
    │   │   ├── icon-add.png
    │   │   ├── icon-aside.png
    │   │   ├── icon-close-gray.png
    │   │   ├── icon-close-red.png
    │   │   └── icon-close.png
    │   └── fab.png
    ├── 📂 js
    │   ├── app.js
    │   ├── 📂 controllers
    │   │   └── boardController.js
    │   ├── 📂 stores
    │   │   └── boardStore.js
    │   ├── 📂 utils
    │   │   └── fetch.js
    │   ├── 📂 viewModels
    │   │   └── boardViewModel.js
    │   └── 📂 views
    │       ├── 📂 component
    │       │   ├── board.js
    │       │   ├── card.js
    │       │   └── column.js
    │       └── popup.js
    └── 📂 scss
        ├── 📂 components
        │   ├── _card.scss
        │   ├── _column.scss
        │   ├── _header.scss
        │   ├── _history.scss
        │   ├── _layout.scss
        │   └── _popup.scss
        ├── main.scss
        └── 📂 utils
            ├── _common.scss
            ├── _mixin.scss
            ├── _reset.scss
            └── variables
                ├── _color.scss
                └── _font.scss
```

<br>

## 🕹 How to run

1. 터미널에 `npm run dev`를 입력한다.

```
npm run dev
```

2. http://localhost:9000/ 에 접속한다.
