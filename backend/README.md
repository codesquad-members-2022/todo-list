# backend

### local database

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 실행

```shell
docker-compose -f docker/local-database.yml up -d
```

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 종료

```shell
docker-compose -f docker/local-database.yml down
```

** 배포 시 EC2 인스턴스 내에서 도커를 이용해 데이터베이스 서버를 실행하지 않고,  
인스턴스 내에 직접 데이터베이스를 설치합니다.
