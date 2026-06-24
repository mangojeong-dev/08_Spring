<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head><meta charset="UTF-8"><title>게시글 상세</title></head>
<body>
<h1><c:out value="${board.title}"/></h1>
<p>작성자: <c:out value="${board.writer}"/></p>
<pre><c:out value="${board.content}"/></pre>
<h2>첨부파일</h2>
<ul>
    <c:forEach items="${attachments}" var="file">
        <li>
            <a href="${pageContext.request.contextPath}/board/attachments/download?id=${file.id}">
                <c:out value="${file.originalName}"/>
            </a>
            (${file.fileSize} bytes)
        </li>
    </c:forEach>
</ul>
<p><a href="${pageContext.request.contextPath}/board/list">목록</a></p>
</body>
</html>
