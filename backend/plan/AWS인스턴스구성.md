# AWS 인스턴스 구성

요구사항에 Spring boot와 mysql을 하나의 인스턴스 생성하라고 되어있어서 보안을 위해 ```Bastion 인스턴스와 NAT,ELB```로 구성을 추가해주었습니다.

<br/>

![aws_instance_architecture](https://user-images.githubusercontent.com/86910955/162560191-5eed8f39-b5f3-4aa6-8d70-da7d131dbe10.png)

<br/>

Bastion 서버는 다른말로는 Jumping Host라고도 하며 인터넷 망에서 프라이빗 리소스에 접근하기 위해 거쳐가는 ```프록시 서버의 역할```을 합니다.
즉, 어떤 특별한 기능을 하지 않고 오직 "거쳐가는 용도" 입니다. 같은 VPC 내부의 리소스는 같은 LAN이기때문에<br/>
서로 Private IP로 접근 가능해서 Bastion host를 외부망과 연결하고<br/>
관리자는 Bastion host에 접속하여 Private 리소스에 재접속하거나 터널링합니다.

Bastion Host가 아키텍처에서 이중화되지 않은 이유는 바로 이것 때문입니다.<br/>
애플리케이션에 상관없이 오직 "관리자의 프라이빗 리소스 접근 용도"로만 사용되기 때문에 굳이 이중화하지 않은 것입니다.<br/>

로드밸런서는 80(HTTP) 요청을 Application으로 보내주는 역할을 합니다.<br/>

NAT Gateway는 인터넷과 직접 통신할 필요가 있기 때문에 Public Subnet에 위치해야합니다.<br/>
프라이빗 서브넷의 애플리케이션 인스턴스는 인터넷에서 인바운드되어서는 안되지만 jdk나 소스코드 다운을 위해 아웃바운드는 되어야합니다.<br/>
즉, 애플리케이션 인스턴스는 이 NAT Gateway를 통해 인터넷에서 특정 소스를 받아와야 하기때문에 라우팅 테이블의 설정이 필요합니다.<br/>
