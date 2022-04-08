# MYSQL 설치 과정


## 1. mysql8.0.28 설치

- 레드햇 계열 리눅스이기때문에 yum 명령어를 사용했습니다.

```
sudo yum update
sudo yum install https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm -y
sudo rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
sudo yum install mysql-community-server -y
```

<br/>

## 2. mysql 실행 및 실행 확인

```
sudo systemctl start mysqld
sudo systemctl status mysqld
```

<br/>

## 3. 서버에 mysql 설치가 완료되었으면 root 계정의 임시 비밀번호를 확인한다.

```
sudo grep 'temporary password' /var/log/mysqld.log
```

<br/>

## 4. 임시 비밀번호로 mysql에 접속하여서 비밀번호의 설정 레벨을 낮춘다. low레벨이 아니면 설정하기 너무 어려움.

```
sudo mysql -u root -p
```

비밀번호 : 임시비밀번호 입력

<br/>

## 5. 초기 세팅시 root 비밀번호를 새롭게 변경해주어야합니다.

- 임시 비밀번호로 로그인시 비밀번호를 새롭게 세팅해주어야합니다.
- 초기 설정은 영어 대문자,소문자,특수문자,숫자를 8자리이상으로 사용해야함.

```
ALTER USER 'root'@'localhost' identified by 'new_password';
```

<br/>

## 6. 비밀번호 권한이 어떻게 설정되어있나 확인

```
SHOW VARIABLES LIKE ‘validate_password%’;
```

<br/>

## 7. 권한 레벨 LOW 설정

```
SET GLOBAL validate_password.policy=LOW;
```

<br/>

## 8. 비밀번호 길이 4이상으로 가능하게 설정

```
SET GLOBAL validate_password.length = 4;
```

<br/>

## 9. root 계정의 비밀번호 좀 단순하게 다시 설정한다.

- 대문자, 소문자, 특수문자를 다쓰기 좀 번거로워서 간단하게 바꿨습니다.

```
sudo mysql_secure_installation -p
```

<br/>

## 10. 바뀐 비밀번호 DB에 서버의 root권한으로 접속한다.

```
sudo mysql -u root -p
```

<br/>

## 11. 새로운 유저 생성

```
CREATE USER '계정명'@'본인의 IP' IDENTIFIED BY '1234';
ex) CREATE USER 'minri'@'%' IDENTIFIED BY '1234';
```

<br/>

## 12. IP 접근 권한 허용

```
GRANT ALL PRIVILEGES ON *.* TO 'root 또는 원하는계정'@'본인IP';
Ex)GRANT ALL PRIVILEGES ON *.* TO 'meenzino'@'%';  <- %는 모든 IP허용
```

<br/>

## 13. 변경 사항 적용

```
FLUSH PRIVILEGES;
```

<br/>

## 14. 변경 사항이 제대로 적용이 되지 않았을 경우 ALTER 명령어를 통해 테이블의 내용을 수정한다.

```
ALTER USER '계정명'@'*' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY '패스워드';
```

<br/>

## 15. 잘 적용되었는지 확인

```
SELECT HOST, USER FROM USER;
```
