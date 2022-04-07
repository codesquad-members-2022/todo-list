# todo-list
그룹 프로젝트 #1



### 팀원소개

#### Backend 

- 민지노 : 백엔드 빈지노 
- 테리 : 백엔드 거북이

#### iOS

- 푸코 : iOS 폭주족 
- 체즈 : iOS 락스타



### 브랜치전략

:  각 포지션에 맞게 `iOS-develop` `backend-develop` 에서 기능별로 브랜치를 만들어 작업한다  

:  `feature/기능`  구현이 완료되면 각 포지션 브랜치에 `merge `  한다 

:  기능 구현이 완료된 `iOS-develop` `backend-develop`  브랜치를 `upstream` 에 PR을 보낸다. 

: PR이 반영된후 `develop` 에 `merge` 한다. 



upsteam

​	ㄴteam-15

origin 

​	 ㄴdevelop

​			ㄴiOS-develop

​				    ㄴ feature/기능1

​				    ㄴ feature/기능2

​            ㄴbackend-develop

​					 ㄴ feature/ 기능1

​					 ㄴ feature/ 기능2

​					



### 그라운드룰

- 그룹회의 
  -  오전10시 : 가벼운 이야기와, 전날 어떤작업을 했는지 간단하게 공유 
  -  오후5시 : 그날 작업한 진행상황 디테일하게 공유, 서로 협의해야할 문제 논의
- 오프라인 작업
  - 일주일에 한번 오프라인으로 만나 작업/진행상황 공유
- 합의하에 저녁시간 이후에도 작업 진행 (강요x)

- 실수해도 서로 웃어주기 

- 기능구현보다는 협업/학습 위주로 해보기 

- 의견/질문 적극적으로 내기

  





### 커밋 템플릿

FEAT : 새로운 기능의 추가

FIX: 버그 수정

DOCS: 문서 수정

STYLE: 스타일 관련 기능(코드 포맷팅, 세미콜론 누락, 코드 자체의 변경이 없는 경우)

REFACTOR: 코드 리펙토링 

TEST: 테스트 코트, 리펙토링 테스트 코드 추가

CHORE: 빌드 업무 수정, 패키지 매니저 수정(ex .gitignore 수정 같은 경우)





### 회의록 

2022.04.04 : 
