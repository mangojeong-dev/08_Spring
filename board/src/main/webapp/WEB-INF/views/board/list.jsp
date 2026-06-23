<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 자바의 핵심문법인 for, if등과 같은 문법을 태그를 이용해 코딩하고자 하는 경우, 태그(jstl) 등록
이 역할을 하는 태그는 모두 앞에 접두사로 c를 붙이겠음.
-->
<!--  문자열 출력시 원하는 포맷(형태)로 변환하는 태그(jstl) 등록
이 역할을 하는 태그는 모두 앞에 접두사로 fmt를 붙이겠음.
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp" %>
<%-- 개별 페이지 --%>
<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>

    <!-- controller에서 설정한 list속성값을 하나씩 꺼내 board변수에 넣어주는 for문(태그이용) -->
<c:forEach items="${list}" var="board">
    <tr>
        <td>
            <!-- board의 no값 출력 -->
            ${board.no}
        </td>
        <td>

            <!-- board의 title값을 출력했을 때 board.no를 전달하면서 하나의 게시글 검색하는 get요청 설정 -->
            <a href="get?no=${board.no}">${board.title}</a>

        </td>
        <td>
            <!-- board의 writer값 출력 -->
                ${board.writer}

        </td>
        <td>
            <!-- regDate 출력, 포맷을 지정해서 -->
            <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<div class="text-end">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>
<%@ include file="../layouts/footer.jsp" %>