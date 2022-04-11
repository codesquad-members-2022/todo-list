## 👩‍💻🧑‍💻 팀원소개

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/BB-choi">
        <img src="https://avatars.githubusercontent.com/u/78826879?v=4" width="100px;" alt="BB"/><br />
        <sub><b>BB(FE)</b><br></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/healtheloper">
        <img src="https://avatars.githubusercontent.com/u/58503584?v=4" width="100px;" alt="Park"/><br />
        <sub><b>파크(FE)</b><br></sub>
      </a>
    </td>
  </tr>
</table>

## 💻 협업전략

### 그라운드룰

- 개발시간에 토크(인터럽션)를 자유롭게
  - 쉬는 시간, 궁금한거, 등등
- `Core` 가 될만한 공통적인 코드는 수정시 어떻게 진행할지 공유

## 🌳 브랜치 관련

### 브랜치 구조

<details markdown="1">
<summary>1차 계획</summary>

- 반장이 코드스쿼드 저장소를 fork
- 다른 그룹원이 반장 저장소를 fork

- team-22 > FE, BE 브랜치 생성
- FE -> BB/feat1, park/feat1
- BE -> BE/feat1 ...

- FE 에서 기능을 위한 브랜치 생성
- 해당 브랜치에서 작업 후 FE 로 PR 보내기 (그룹원들이 확인후 Confirm)
- FE 에서 코드스쿼드 레포지토리로 PR 보내기
- 실제 작업할 공간 외에 [연습용 저장소](https://github.com/codesquard-fe-park/git-practice)를 만들어서 연습

  ![image](https://user-images.githubusercontent.com/58503584/161499318-50e8a3ac-e50e-4051-be88-a5ae9252a718.png)

  ![image](https://user-images.githubusercontent.com/58503584/161927829-63307a56-f41f-438b-bcda-dfdf2d2a3717.png)

</details>
<br>

<details markdown="1">
<summary>2차 계획</summary>

- FE, BE 가 따로 PR 을 보내는 경우를 생각하여 FE, BE 를 나누어서 작업했으나, 우리조가 FE-BE 로 나뉘지 않았음에도 PR 을 두 번 보내야 하는 일이 생김
- 리뷰어 분에게 여쭤보고, 하나의 브랜치로 통합하여 1 번만 보내는 것으로 결정
- 위와 같은 이유로 브랜치 전략을 재 수정 하였습니다.

<img width="681" alt="스크린샷 2022-04-11 오후 9 58 28" src="https://user-images.githubusercontent.com/58503584/162753171-2648ec98-12aa-4fdf-9089-cf3b9a593588.png">

</details>
<br>

### 브랜치 네이밍

`<tag>(/short-description)/<issue-id>`

- 예시)

```
fix/indentation-error/122
issues/update-documentaion/145-134-133 // 이슈 여러 개
issues/focus-blur/124 // 마스터 이슈
refactor/correct-typos/123
feat/multiline-ternary-option/44
issue/npm-publish/13
```

## 📔 커밋 템플릿

`<Type>: Short description`

- ex) feat: Create Button Component

| 타입             | 설명                                                                      |
| ---------------- | ------------------------------------------------------------------------- |
| Feat             | 새로운 기능을 추가할 경우                                                 |
| Fix              | 버그를 고친 경우                                                          |
| Design           | CSS 등 사용자 UI 디자인 변경                                              |
| !BREAKING CHANGE | 커다란 API 변경의 경우                                                    |
| !HOTFIX          | 급하게 치명적인 버그를 고쳐야하는 경우                                    |
| Style            | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우                     |
| Refactor         | 프로덕션 코드 리팩토링                                                    |
| Comment          | 필요한 주석 추가 및 변경                                                  |
| Docs             | 문서를 수정한 경우                                                        |
| Test             | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)                        |
| Chore            | 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
| Rename           | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우                        |
| Remove           | 파일을 삭제하는 작업만 수행한 경우                                        |
| Env              | 환경설정이 변경된 경우                                                    |

[커밋 타입 출처](https://overcome-the-limits.tistory.com/entry/협업-협업을-위한-기본적인-git-커밋컨벤션-설정하기)

## 📔 PR 템플릿

### 제목

`[<who>] short-description`

- ex) [BB] multiline-ternary-option

### Template

```
issue #{num}
{필요시 할말}

-- 이부분은 확인 후 지워주세요 --
제목 컨벤션 -> `[<who>] short-description`
본문에 이슈번호 태그 -> ex) issue #37
Projects 에는 올리지 않아도 됩니다 ( 요구기능은 issue 로 관리 중 )
```

## 📔 이슈 템플릿

### Label

`fe-feat`, `fe-bug`, `be-feat`, `예상소요시간-{}`

### Template

```
## 예상 동작

-- 이부분은 확인 후 지워주세요 --
제목은 자유롭게 작성
클래스에 맞추어 `Projects` 설정 해주세요 (ex. FE -> todolist-fe)
```

## 🔗 회의록/이슈/위키 연결 등

- 프로젝트 탭 활용하여 `issue-PR` 연결하여 사용

## BE Mock Server

- [JSON server](https://github.com/typicode/json-server)를 사용하여 API 통신
- 담당: @[파크](https://github.com/healtheloper)
  - JSON server 사용하려다가 router 설정의 한계를 느껴서 express + mongoDB + mongoose 로 변경하였습니다.
