# spring-advanced-assignment

Spring Framework 5.3 기반 Legacy WAR 프로젝트입니다. Spring Boot를 사용하지 않으며
Java Config 기반 DispatcherServlet, JSP, AOP, JDBC BLOB 첨부파일, STOMP 채팅을 포함합니다.

## Database

1. `src/main/resources/sql/schema.sql`을 MySQL에 실행합니다.
2. 필요하면 환경변수를 지정합니다.

```powershell
$env:DB_URL='jdbc:mysql://localhost:3306/spring_advanced_assignment_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8'
$env:DB_USERNAME='root'
$env:DB_PASSWORD='password'
```

## Build and test

```powershell
.\gradlew.bat clean test war
```

생성된 WAR는 `build/libs/spring-advanced-assignment-1.0-SNAPSHOT.war`이며
Servlet 4 호환 Tomcat 9에 배포합니다.
