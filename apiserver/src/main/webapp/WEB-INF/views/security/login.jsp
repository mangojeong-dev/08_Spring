<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="yellow">

<h1>로그인 화면</h1>
<form method="POST" action="/security/login">
<%--    서버에서 form을 브라우저로 보낼 때 csrf공격을 막기위해서
        토큰을 만들어 form에 숨겨넣어 보냄.
--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <table>
        <tr>
            <td>사용자 id: </td>
            <td>
                <input type="text" name="username">
            </td>
        </tr>
        <tr>
            <td>사용자 pw: </td>
            <td>
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="Login" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>
