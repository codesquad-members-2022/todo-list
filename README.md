# Todolist

### 팀원 소개

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/mina-gwak">
        <img src="https://avatars.githubusercontent.com/u/62706988?v=4" width="100px;" alt="Jamie"/><br />
        <sub><b>Jamie</b><br></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/JinJeon">
        <img src="https://avatars.githubusercontent.com/u/67730358?v=4" width="100px;" alt="Pokky"/><br />
        <sub><b>JinJeon</b><br></sub>
      </a>
    </td>
  </tr>
</table>
<br/>

### 협업 전략

<img src="https://user-images.githubusercontent.com/62706988/161683909-9ae10e7b-3ff7-4df2-84c3-a2e9ed565588.jpeg" height="500px" alt="협업 전략" />

- [Git Flow 전략](https://techblog.woowahan.com/2553/)을 사용한다.
- `develop` 브랜치에서 기능 단위로 `feature/기능` 브랜치를 생성하여 개발한 후, 구현이 완료되면 `develop` 브랜치에 반영한다.
- PR을 보낸 후 머지가 완료된 브랜치를 `Fetch` 하여 위 과정을 반복한다.
  <br/>


### 그라운드 룰

1. 오후 5시 30분에 마무리 리뷰 ∙ 회고 시작
   (단, 내용이 적을 시 합의 하에 45분으로 미룰 수 있다.)
2. 화요일 ∙ 목요일 2시 부터 회의 진행
   1. 현재 진행 사항 공유 및 피드백
   2. 추후 진행할 작업 계획 및 분담
3. 오전 스크럼 시 10분간 잡담하기
4. 궁금하거나 의논할 사항이 있을 시 마이크를 켜고 바로 질문하기
5. 개발 도중에는 구현 사항에 대해서 노션에 간략하게 정리를 진행하고, PR 전 자세한 설명이나 추가 내용을 작성하여 리드미에 추가한다.
   <br/>


### 커밋 템플릿

| feat | 새로운 기능 추가 |
| --- | --- |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 스타일 관련 기능 |
| refactor | 코드 리팩토링 |
| test | 테스트 코드, 리팩토링 테스트 코드 추가 |
| chore | 빌드 업무 수정, 패키지 매니저 수정 |
- 영문으로 작성
- 상황에 따라 여러 파일에 대한 설명이 필요한 경우에는 부가적인 설명을 아래에 적어주도록 함
  <br/>

```markdown
예시 1
feat: Change banner automatically

예시 2
refactor: Category refactoring

- CategoriesView.js : Create view class for category
- CategoriesModel.js : Create model that bring category data
```
