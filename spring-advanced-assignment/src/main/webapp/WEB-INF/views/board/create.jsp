<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head><meta charset="UTF-8"><title>게시글 작성</title></head>
<body>
<h1>게시글 작성</h1>
<form method="post"
      action="${pageContext.request.contextPath}/board/create"
      enctype="multipart/form-data">
    <p><label>제목 <input name="title" required maxlength="200"></label></p>
    <p><label>작성자 <input name="writer" required maxlength="50"></label></p>
    <p><label>내용<br><textarea name="content" rows="10" cols="60"></textarea></label></p>
    <p><label>첨부파일 <input type="file" name="attachment"></label></p>
    <button type="submit">저장</button>
</form>
</body>
</html>
