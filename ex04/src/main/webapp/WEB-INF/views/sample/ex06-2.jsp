<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2026-06-17
  Time: 오후 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
ex06-2 페이지 <br>
<h3>@RequestParam으로 전달받은 경우 EL로 표현할 경우 param.속성명 형식으로 표시해야 한다</h3>
name : ${param.name} <br>
age : ${param.age} <br><br>

<h3>@ModelAttribute로 전달받은 경우 : DTO 클래스명(소문자 시작).속성명</h3>
name : ${dto.name} <br>
age : ${dto.age} <br>

</body>
</html>
