## 팀원소개

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

## 협업전략

- BE Mock Server

  - [JSON server](https://github.com/typicode/json-server)를 사용하여 API 통신
  - 담당: @[파크](https://github.com/healtheloper)

- 브랜치 구조

  - 반장이 코드스쿼드 저장소를 fork
  - 다른 그룹원이 반장 저장소를 fork

  - team-22 > FE, BE 브랜치 생성
  - FE -> BB/feat1, park/feat1
  - BE -> BE/feat1 ...

- FE 에서 기능을 위한 브랜치 생성
- 해당 브랜치에서 작업 후 FE 로 PR 보내기 (그룹원들이 확인후 Confirm)
- FE 에서 코드스쿼드 레포지토리로 PR 보내기

![image](https://user-images.githubusercontent.com/58503584/161499318-50e8a3ac-e50e-4051-be88-a5ae9252a718.png)

- 브랜치 네이밍

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

## 그라운드룰

- 개발시간에 토크(인터럽션)를 자유롭게
  - 쉬는 시간, 궁금한거, 등등
- Core 가 될만한 공통적인 코드는 수정시 어떻게 진행할지 공유

## 커밋 템플릿

`<Type>: Short description (#<issue-id>)`

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

출처: https://overcome-the-limits.tistory.com/entry/협업-협업을-위한-기본적인-git-커밋컨벤션-설정하기 [Plus Ultra]

## PR 템플릿

### 제목

`[<who>] short-description (<type> #<issue-id>)`

- [BB] multiline-ternary-option (feat #44)

### Template

```
## 필요시 할말
```

## 이슈 템플릿

### Label

`fe-feat`, `fe-bug`

### Template

```
## 예상 동작

## (버그시) 관련된 코드

## (버그시) 어떻게 동작했을 때 생기는지
```

## 회의록/이슈/위키 연결 등

- 프로젝트 탭 활용하여 `issue-PR` 연결하여 사용
