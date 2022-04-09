# 📚 Todo List

2022년 코드스쿼드 Todo List 프로젝트.

[@Jun(BE)](https://github.com/devjun10), [@검봉(BE)](geombonghttps://www.github.com/geombong)
, [@Pigbag(IOS)](https://github.com/P-SeoB), [@카이(IOS)](https://github.com/TaeKyeongKim)

<br/>

<img src="https://user-images.githubusercontent.com/92818747/161917086-e76f606a-ae11-4953-bfc6-ac25c592b8c2.png" width="" height="">


<br/><br/><br/>

## 📌 규칙

스크럼과 깃 두 가지에 대해 규칙을 정하고 프로젝트를 진행했습니다.

<br/><br/>

### 스크럼

스크럼은 오전/오후 두 번의 공식적 스크럼 타임을 가지며, 너무 늦은 시간이 아니라면 상시로 연락을 주고받습니다.
<br/><br/>

|종류|시간|내용|
|:------|:---:|:----| 
|&nbsp;&nbsp;데일리 스크럼 [AM]|10:00 ~ 10:30|&nbsp;&nbsp; 진행사항 공유 및 회의. 일일 계획 발표 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|
|&nbsp;&nbsp;데일리 스크럼 [PM]|17:30 ~ 18:00|&nbsp;&nbsp; 진행사항 공유 및 피드백. 간단한 일일 회고|

<br/><br/><br/>

### Git

`브랜치 관리`, `커밋 메시지`에 대해 규칙을 정했습니다. 

- 각 직군별 feature/develop 브랜치를 만들고 `각자의 feature 브랜치에서 작업을 진행`합니다. ex) feature/bong
- 이후 `각 직군별 develop 브랜치`로 merge합니다. ex) b-develop, i-develop
- PR/배포가 있을 경우 각 직군 별 develop 브랜치, 미션 제출의 경우 master 브랜치와 같이 `각 상황에 맞는 브랜치를 이용`합니다.

<br/>

<img src="https://user-images.githubusercontent.com/92818747/161921360-b38bbe8a-f16b-4a7d-8fee-43c6d839492f.png" width="90%" height="90%">

<br/><br/><br/><br/>

커밋 메시지는 Udacity의 [커밋 메시지 가이드](https://udacity.github.io/git-styleguide/) 를 참조했습니다.

|종류|내용|
|:------|:----|
|&nbsp;&nbsp;feat|&nbsp;&nbsp;새로운 기능 추가|
|&nbsp;&nbsp;fix|&nbsp;&nbsp;버그 수정|
|&nbsp;&nbsp;docs|&nbsp;&nbsp;문서 수정|
|&nbsp;&nbsp;style|&nbsp;&nbsp;코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우|
|&nbsp;&nbsp;refactor|&nbsp;&nbsp;코드 리팩토링|
|&nbsp;&nbsp;test|&nbsp;&nbsp;테스트 코드, 리팩토링 테스트 코드 추가|
|&nbsp;&nbsp;chore|&nbsp;&nbsp;빌드 업무 수정, 패키지 매니저 수정|


<br/><br/><br/><br/>



## 🔖 구현/학습 중점사항

BE와 IOS가 프로젝트에서 중점을 두고 있는, 학습할(한) 부분을 정리했습니다.

<br/>

### BE

다른 직군과의 첫 협업이기 때문에 의사소통에 최중점을 두었습니다. `클라이언트의 요구사항을 어떻게 하면 잘 충족시켜줄 수 있을지`, `보이지 않는 시스템적인 부분을 어떻게 잘 설명할 수 있을지`를 특히 많이
고민했습니다.

- `빠른 의사소통을 위한 친해지기`, `말 많이 하기`
- IOS 요구사항, `기획서 분석/공부`
- `백엔드 개발 외적 요소들`과 `사용자 입장 생각`해보기
- `백엔드 요구사항 학습`

<br/><br/>

### IOS

- [ ] `오토레이아웃`을 적용하여 다양한 아이패드에서도 동일한 View를 보여준다.
- [ ] MVC 구조를 학습하고 `입출력을 명확하게 구분`한다.
- [ ] ViewController에 역할을 세분화하여 `분리`한다.
- [ ] `Container ViewController` 와 `Child ViewController` 관계를 이해하고 활용한다.
- [ ] View의 `Self-Resizing`을 구현한다.
- [ ] `드래그 앤 드롭 기능`을 학습하고 TableView에 적용한다.
- [ ] URLSession을 활용 `비동기적`으로 서버에 요청을 하고 화면에 반영한다.
- [ ] `Context Menu API`를 학습, 활용하여 ContextMenu를 TablView에 구현한다.
- [ ] App 과 웹 `사용자를 구분`해서 표시한다.

<br/><br/><br/>
