# todo-list

그룹 프로젝트 #1

## 팀원소개

dott - https://github.com/godhyeongman

oliver - https://github.com/jthw1005

## 그라운드 룰

- 마이크 계속 키고 개발하기

- PR 받은 날, 서로의 코드에 대해 설명하기 (리뷰후)

- 오후 출석 체크

- 오전 스크럼 + 오후 스크럼 + 저녁 스크럼

- 스크럼 때 할 얘기: 할 일 얘기하기 / 저녁에는 문제에 대해서 의논하기

## 협업 전략

1. 마크업 환경설정등 뼈대가 되어 주는 작업은  
   함께 페어로 작업 후 분업이 가능한 시점에서 분업을 진행하기로 결정

2. 브랜치를 고용 브랜치, 개인 브랜치 나누어 사용하기로 결정

3. 설계 함께함, 계획에 있어 수정이 필요한 부분은 서로 상의 후 진행한다.

4. 백로그?? 해야할 일을 깃헙 이슈에 저장하여 함께 볼 수 있도록 계획 중

## 깃 메세지 컨벤션

| **제목** | **내용**                     |
| -------- | ---------------------------- |
| Feat     | 새로운 기능을 추가           |
| Fix      | 버그를 수정                  |
| Docs     | 문서를 수정                  |
| Style    | 스타일을 수정                |
| Refactor | 리팩토링                     |
| Design   | css등 사용자 UI 변경         |
| Comment  | 주석 추가 및 변경            |
| Rename   | 파일 혹은 폴더명을 수정&옮김 |
| Remove   | 파일을 삭제하는 작업만 수행  |
| Chore    | 파일 생성 및 잡일            |

## 깃 브랜치 컨벤션

https://gmlwjd9405.github.io/images/types-of-git-branch/total-branch.png

```.
└── team-30(main)
   └── developer
        ├── oliver feature
        │
        └── dott feature

```

1. develop 브랜치: 공용 개발 브랜치(현재는 환경설정)

   - pr 보내는날 feature 병합

2. feature 브랜치: 개인 개발 브랜치

   - 개별 브랜치 이안에서 issue,test브랜치등이 나뉠거라 예상중

3. 배포 브랜치, 이슈브랜치는 아직? 어떻게 활용할지 미정

## 디자인, 설계

피그마 에서 공용 설계

## 현 계획

HTML,CSS 마크업 -> postCSS

js 디자인 패턴 -> MVC

백엔드 구성 -> JSON 서버

## 웹팩 & 바벨 설치 항목

```
├── @babel/cli@7.17.6
├── @babel/core@7.17.8
├── @babel/plugin-proposal-function-bind@7.16.7
├── @babel/preset-env@7.16.11
├── babel-loader@8.2.4
├── core-js@3.21.1
├── css-loader@6.7.1
├── sass-loader@12.6.0
├── sass@1.49.11
├── style-loader@3.3.1
├── webpack-cli@4.9.2
└── webpack@5.71.0
```
