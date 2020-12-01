<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>
<body>

<%--<h1>회원 정보입력</h1>--%>

<%--<p>--%>
<%--    <a href="/user/info">감염경로의 알람을 받으려면 입력해주세요</a>--%>
<%--</p>--%>


<div class="container">
    <form action="/user/info" method="get">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="핸드폰 번호를 입력하세요">
        </div>
        <button type="submit">등록</button>
    </form>
</div>

</body>
</html>
