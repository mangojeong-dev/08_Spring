<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../layouts/header.jsp"%>
<h1>OpenAPI 이용한 날씨정보</h1>
<div>
    <h2>${city}</h2>
    오늘의 날씨: ${weather.weather[0].description} <img src="${iconUrl}"/></div>
<div>
    온도: ${weather.main.temp}° / 습도: ${weather.main.humidity}%
</div>
<%@ include file="../layouts/footer.jsp" %>
</body>
</html>