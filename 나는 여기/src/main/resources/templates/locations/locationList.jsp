<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LocationList</title>
</head>
<body>

    <div class="container">
        <div>
            <h1>위치목록</h1>
            <table>
                <thead>
                <tr><th>#</th>
                    <th>Latitude</th>
                    <th>Longitude</th>

                </tr>
                </thead>
                <tbody>
                <tr th:each="location : ${locations}">
                    <td th:text="${location.id}"></td>
                    <td th:text="${location.Latitude}"></td>
                    <td th:text="${location.Longitude}"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div> <!-- /container -->

</body>
</html>
