# 2022.04.06 EC2 인스턴스 생성

애플리케이션과 mysql을 설치할 EC2인스턴스를 생성해보겠습니다.

### 1. 인스턴스 시작

![인스턴스_시작](https://user-images.githubusercontent.com/86910955/161945949-4e9528d1-ef82-483f-9f67-85ca89eafff8.png)

- 인스턴스를 서울 리전에 생성하기 위해 인스턴스의 리전을 서울로 바꿔준 후 인스턴스 시작 클릭

### 2. Amazon Machine Image(AMI) 선택

![AMI선택](https://user-images.githubusercontent.com/86910955/161945966-8762486a-9a8c-4a85-95fa-521cedd876b0.png)

- 지금까지 계속 Ubuntu 이미지로만 생성을 했었는데 이번에는 다른 계열의 리눅스도 사용해보고 싶어서 레드햇 계열의 Amazon Linux 2 AMI를 선택했습니다.
- 아마존 리눅스를 사용시에 메리트
  - 아마존이 개발하고 있기 때문에 서포트 받기가 쉽다.
  - 레드햇 베이스 이므로 레드햇 계열의 배포판을 많이 다뤄본 사람일 수록 문제 없이 사용가능하다.
  - Amazon Web Service의 각종 서비스와의 상성이 좋다. 
  - Amazon 독자적인 개발 리포지터리를 사용하고 있어 yum이 매우 빠르다.

### 3. 인스턴스 유형 선택

![인스턴스_유형_선택](https://user-images.githubusercontent.com/86910955/161945973-94a2c4ca-94c4-4632-bf05-7a8c63a73d50.png)

- 인스턴스 유형이란 머신의 사양이며 용도에 따라 선택할 수 있습니다.
- 프리티어의 경우 성능은 vCPU 1,메모리 1GiB, 네트워크 1Gbps 입니다.


### 4. 인스턴스 세부 정보 구성

![인스턴스_세부_정보_구성](https://user-images.githubusercontent.com/86910955/161945983-a8d4afdd-a394-421c-8068-ff7cfd80624e.png)

- 초기에는 네트워크 부분을 디폴트 값으로 생성하고 추후에 VPC를 변경할 예정입니다.


### 5. 스토리지 추가

![스토리지_추가](https://user-images.githubusercontent.com/86910955/161945988-847e287c-6f4b-43ad-8c0b-a55d125d7d1e.png)

- 프리티어의 경우 최대 30GB로 생성할 수 있어서 30GB로 설정해주었습니다.


### 6.태크 추가

![태그_추가](https://user-images.githubusercontent.com/86910955/161945995-9537856d-6eed-40d3-aa0e-5c7b7d2c23ac.png)

- 해당 인스턴스가 어떤 서버인지 잘 확인할 수 있도록 Name 태그를 달아주었습니다.


### 7. 보안 그룹 구성

![보안_그룹_구성](https://user-images.githubusercontent.com/86910955/161946003-ad879316-c64c-473f-8173-1890602f27ff.png)

- 보안그룹은 api 요청을 위한 80(HTTP)포트와 터미널로 접속하기 위한 22(SSH)포트, MYSQL(3306)포트를 열어주었습니다.
- 지금은 모든 호스트를 허용해주었지만 추후에 MYSQL 소스범위를 테리와 민지노의 IP만 허용할 예정입니다.

### 8. 인스턴스 시작 검토

![인스턴스_시작_검토](https://user-images.githubusercontent.com/86910955/161946011-a2f5c898-3894-4a53-bb27-3bcc3ef8360f.png)

- 이제 마지막으로 지금까지 잘 설정을 해주었는지 확인 후에 인스턴스를 생성합니다.

![탄력적_IP_설정](https://user-images.githubusercontent.com/86910955/161952873-2e37d287-8510-4bc5-9666-55f4b95ca4c5.png)

- 계속 같은 IP를 쓰기위해 탄력적 IP를 설정해주었습니다.
