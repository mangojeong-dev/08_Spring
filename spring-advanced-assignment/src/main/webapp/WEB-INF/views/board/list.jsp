<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head><meta charset="UTF-8"><title>게시판</title></head>
<body>
<h1>게시판</h1>
<p><a href="${pageContext.request.contextPath}/board/create">새 글 작성</a></p>
<table border="1" cellpadding="8">
    <thead>
    <tr><th>ID</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${boards}" var="board">
        <tr>
            <td>${board.id}</td>
            <td><a href="${pageContext.request.contextPath}/board/get?id=${board.id}">
                <c:out value="${board.title}"/>
            </a></td>
            <td><c:out value="${board.writer}"/></td>
            <td>${board.createdAt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
