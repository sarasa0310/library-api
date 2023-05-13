# 도서 관리 API 프로젝트

### 개요
- 하나의 도서관을 가정해 대출 - 반납 서비스를 구현한 미니 프로젝트입니다.
- **Java**와 **Spring Boot**를 기반으로 하고 있습니다.
- **AWS Elastic Beanstalk**로 애플리케이션을 배포하였습니다.

<br/>

### 주요 기능
1. 도서관이 보유하고 있는 책을 검색할 수 있습니다. 제목을 기준으로 찾을 수도 있어요!
2. 책을 대출하고 반납할 수 있습니다. 대출기한은 14일이고, 5권까지 빌릴 수 있어요.
3. 대출을 하려면 사용자로 가입해야 해요. 탈퇴도 가능합니다.
4. 자신의 대출 기록도 조회할 수 있어요.

<br/>

### API 엔드포인트
**1. 도서 검색**
- GET http://library.ap-northeast-2.elasticbeanstalk.com/books

**2. 도서 대출**
- POST http://library.ap-northeast-2.elasticbeanstalk.com/books/loan

**3. 도서 반납**
- PATCH http://library.ap-northeast-2.elasticbeanstalk.com/books/return

**4. 회원 가입**
- POST http://library.ap-northeast-2.elasticbeanstalk.com/users

**5. 회원 탈퇴**
- DELETE http://library.ap-northeast-2.elasticbeanstalk.com/users

**6. 대출 기록 조회**
- GET http://library.ap-northeast-2.elasticbeanstalk.com/users/loan

<br/>

### API 문서
https://documenter.getpostman.com/view/24688565/2s93ebSW33

![api문서](https://user-images.githubusercontent.com/129481038/238102771-9b905b46-bc98-4274-893b-eafe7e8629a1.PNG)

<br/>

### 사용자 요구사항 정의서
https://docs.google.com/spreadsheets/d/103K7Lyva38m-eWdB-7JWMkELyaF2ZrAYLxDYCeysfi4/edit?usp=sharing

![사용자 요구사항 정의서](https://user-images.githubusercontent.com/129481038/238102796-7e6ab131-17ca-4878-8164-40b2f460f806.PNG)

<br/>

### ERD

![erd](https://user-images.githubusercontent.com/129481038/238102798-37d7ab92-4346-416a-9e74-42dea73d1b11.PNG)

<br/>

### 커밋 컨벤션
|  Message  | 	설명                           |
|:---------:|:------------------------------|
|   feat	   | 새로운 기능을 추가할 경우                |
|   fix	    | 버그 수정                         |
|   doc	    | 문서 수정                         |
|  style	   | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| refactor	 | 코드 리펙토링                       |
|   test	   | 테스트 코드, 리펙토링 테스트 코드 추가        |
|  chore	   | 빌드 업무 수정, 패키지 매니저 수정          |
|  rename	  | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우  |
