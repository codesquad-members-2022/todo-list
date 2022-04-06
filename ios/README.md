### IOS

--- 

### ✅ 브랜치 전략 

![무제](https://user-images.githubusercontent.com/92635121/161908826-16ded59f-ada0-4316-9df3-933acc9c15c4.png)

<br>

### ✅ 커밋 컨벤션  
* [작업] #이슈번호 작업내용(동사로 끝나게)  
`[feat] #2 테이블 뷰 커스텀 셀 구현`
* PR과 Commit 시, `이슈 넘버` 남기기

<br>

### ✅ 코딩 컨벤션 

* init 기준 위는, View attribute 설정 상수(컴포넌트) 위치  
* init 기준 아래는, `setUpView()`  와 컴포넌트 순서에 맞는 메서드 

<br>

|메서드|설명|
|---|---|
|setUpView()|addSubView() / 레이아웃 잡은 메서드|
|layout()|컴포넌트 레이아웃|

<br>

#### 자기 자신(self)에 대한 값 변경 시, `configureView()` 메서드 사용
위치는 `setUpView()` 바로 아래

<br>

#### 컴포넌트 속성 변경 시, 익스텐션 사용하기

``` swift
\\ MARK: - Functions

extension 객체 {
    func test() {}
}
```

<br>

### ✅ 네이밍 컨벤션 
* 컴포넌트 생성 시, 뒤에 컴포넌트 명시해주기  
`Ex. addButton / titleLabel` 

