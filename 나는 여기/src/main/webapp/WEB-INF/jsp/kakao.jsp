<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>카카오</title>
</head>
<body>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=e54cdcf9334ea492db2e01c8ebacd5ee&redirect_uri=http://localhost:8080/kcallback&response_type=code">
            <%--        <img src="kakao_login.png">--%>
        <p>카카오 login</p>
    </a>
</body>
</html>
