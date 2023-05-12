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

![api문서](https://private-user-images.githubusercontent.com/115615321/237861986-4b2435b7-1197-4c52-a6a4-c3a25503d161.PNG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjgzODYyNDIxLCJuYmYiOjE2ODM4NjIxMjEsInBhdGgiOiIvMTE1NjE1MzIxLzIzNzg2MTk4Ni00YjI0MzViNy0xMTk3LTRjNTItYTZhNC1jM2EyNTUwM2QxNjEuUE5HP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDUxMiUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA1MTJUMDMyODQxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9ZTYyNjI1M2IxYzY1MzU5M2U2YzgxNWY4MjE5YWMwNWJkNjJjM2I1YTVjYjNhYzZkYThjODZhN2UzY2JlYjA0ZSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.4-pO-xd9K6Z-dFx7NGcdGEp50zXxj0rvxsSdUqgn9tY)

<br/>

### 사용자 요구사항 정의서
https://docs.google.com/spreadsheets/d/103K7Lyva38m-eWdB-7JWMkELyaF2ZrAYLxDYCeysfi4/edit?usp=sharing

![사용자 요구사항 정의서](https://private-user-images.githubusercontent.com/115615321/237862000-007e33a4-bb14-4f68-906c-15607da4cd7f.PNG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjgzODYyNDIxLCJuYmYiOjE2ODM4NjIxMjEsInBhdGgiOiIvMTE1NjE1MzIxLzIzNzg2MjAwMC0wMDdlMzNhNC1iYjE0LTRmNjgtOTA2Yy0xNTYwN2RhNGNkN2YuUE5HP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDUxMiUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA1MTJUMDMyODQxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9ODczNjk5MDJiMDJiN2I4NDRiNzEzZmQ3ZDUzOTM3MjRlMzI0MTMyMTY4OTI2OWJkZjFhMjNkNDhjMWEwODg0NCZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.wKfNYR8_uMGGjxfCHiGJSSONqrHppTkvMLGjc8LxMhM)

<br/>

### ERD

![erd](https://private-user-images.githubusercontent.com/115615321/237862006-5666e320-eea2-46e6-8d61-daabe609c561.PNG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjgzODYyNDIxLCJuYmYiOjE2ODM4NjIxMjEsInBhdGgiOiIvMTE1NjE1MzIxLzIzNzg2MjAwNi01NjY2ZTMyMC1lZWEyLTQ2ZTYtOGQ2MS1kYWFiZTYwOWM1NjEuUE5HP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQUlXTkpZQVg0Q1NWRUg1M0ElMkYyMDIzMDUxMiUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyMzA1MTJUMDMyODQxWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MTMxYjUzOGU4MzE3YWZhNjZhYWZlOTEwODYzOGU0NGFkMzBlN2ViMTIxY2RkNGYwZmQ4NDE4MTlkNWJjYTllYyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.XiH7YXbEF5b6Gar077w48nFRMM5WadWU8o7IucS0MwY)

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
