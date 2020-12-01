<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>
<body>
    <div class="container">
        <div>
            <h1>Hello Spring</h1>
            <p>회원 기능</p>
            <p>
                <a href="/members/new">회원 가입</a>
                <a href="/members">회원 목록</a>
                <a href="/locations/save">위치 저장</a>
                <a href="/locations">위치 목록</a>
            </p>
        </div>
    </div>
    <!-- /container -->
</body>
</html>
