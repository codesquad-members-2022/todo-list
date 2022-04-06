# todo-list

팀명 : iJava

## iJava 팀원 소개

### IOS

- 구찌 : ESTP
- 알렉스 : ISTJ

### BE

- 벅픽 : ISFJ
- Jay : INFP

## 협업 전략

한 repo 에서 ios, be 각각 branch 를 생성하여, 해당 브랜치를 각 분야의 main 브랜치로 활용
```
iJava branch
  ㄴios branch
    ㄴfeature branch
  ㄴbe branch
    ㄴfeature branch
```

1. `기능 개발`은 `ios`, `be` 브랜치에서 `feature` 브랜치 생성하여 진행
2. `기능 개발` 완료 후 `ios`, `be` 로 머지(merge, squash merge)
3. 수, 금 17 시 PR  전에, `be`, `ios` 내부적으로 자체 리뷰 후 PR 보내기

## 그라운드 룰

- commit message : [semantic commit message](https://gist.github.com/jinan159/fc38777ab129f389be5e984e81f1d904)
- 10시 스크럼에 꼭 참석하기