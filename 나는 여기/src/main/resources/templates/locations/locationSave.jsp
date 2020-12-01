<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LocationSave</title>
</head>
<body>

    <p>Click the button to get your coordinates.</p>
    <p id="demo">
        <script>
            var x = document.getElementById("demo");

            function getLocation() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(savePosition);
                } else {
                    x.innerHTML = "Geolocation is not supported by this browser.";
                }
            }

            function savePosition(position) {
                document.getElementById("lat").value = position.coords.latitude;
                document.getElementById("lon").value = position.coords.longitude;
            }
        </script>
    <div class="container">
        <form action="/locations/{uid}>" method="post">
            <div class="form-group">
                <input type="text" id="lat" name="latitude" value="">
                <input type="text" id="lon" name="longitude" value="">
                <input type="button" value="Get location" onclick="getLocation()"/>
            </div>
            <button type="submit">등록</button>
        </form>
    </div>
    </p>

</body>
</html>
