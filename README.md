# todo-list
그룹 프로젝트 1주차 (2022.04.04\~2022.04.08)   
그룹 프로젝트 2주차 (2022.04.11\~2022.04.15)

[현재 작업 상황](https://github.com/seyoung755/todo-list/projects/2)

--- 

## 팀원 소개

### iOS
`주방`: 사랑꾼 주방

`Shingha`: 플래 꿈나무 Shingha

### 백엔드
`Phil`: 번뜩이는 아이디어 Phil라멘트

`산토리`: 산토리니에 사는 산토리

---

## 그라운드 룰
- 매주 화,목,금 오후 5시에 팀 주간 회고 진행
- 미션 진행 중 질문은 zoom에서 자유롭게 소통

---
## 협업 전략

### 작업 상황 

- GitHub 내부 `Projects` 기능을 사용하여 관리
  - 서로 작업하고 있는 상황 공유해서 파악 가능
  - 클래스별로 프로젝트 쪼개서 `할일`에 올리고 담당할 멤버가 정해지면 `작업중`에 업데이트 후 완료되면 `작업완료`
  - 초기 작업 scheme은 4/5(화) 오전까지 클래스별로 정하여 `할일`에 업로드
  - 서버에 배포되어 반영한 내용은 `배포완료`에서 관리

[프로젝트 관리 참고](https://treasurebear.tistory.com/69)

### 소스 코드 폴더
- 백엔드는 backend, iOS는 iOS 폴더에서 소스코드 관리
  
### 브랜치 구조
- `team-14`: 최종 배포 브랜치
- `dev-iOS`: iOS 개발 브랜치
- `dev-be`: 백엔드 개발 브랜치
- `feat-ios/{feature_issue_number}`: iOS 기능별 브랜치
- `feat-be/{feature_issue_number}`: 백엔드 기능별 브랜치
  - feature branch를 통한 개발이 끝나면 PR을 통해 develop branch에 merge한다.
  - PR merge 후 해당 브랜치는 삭제한다.
  - PR merge
    - feature->develop은 클래스 별로 리뷰/확인했다는 comment(LGTM) 남겨주고 서로 merge
    - develop->main은 이번 배포 주기에 대한 기능을 PR하고 동작 확인 후 merge
    - 주차별 개발 목표 설정 후, 개발이 완료된 기능에 대해 배포 진행

### 커밋 컨벤션
`feat` : 새로운 기능 추가  
`fix` : 버그 수정   
`refactor` : 코드 리팩토링  
`style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우   
`test` : 테스트 코드   
`docs` : 문서 수정  
`chore` : 빌드 업무 수정, 패키지 매니저 수정
