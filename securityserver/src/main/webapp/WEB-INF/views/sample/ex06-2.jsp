<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2026-06-17
  Time: 오후 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>@RequestParam으로 전달 받은 경우 : param.속성명 </h3>
    이름 : ${param.name}
    나이 : ${param.age}

    <h3>@ModelAttribute로 전달 받은 경우 : DTO클래스명(소문자시작).필드명</h3>
    이름 : ${sampleDTO.name}
    나이 : ${sampleDTO.age}


</body>
</html>
