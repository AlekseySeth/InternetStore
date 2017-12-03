<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Sports nutrition store</title>
        <style>
        <%@include file="../css/main.css"%>
        <%@include file="../css/header.css"%>
        <%@include file="../css/footer.css"%>
        <%@include file="../css/reset.css"%>
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <h1 class="main-header">Добро пожаловать в интернет-магазин спортивного питания Sport Pit!</h1>
                <p style="text-align: center">
                    <img src="${pageContext.request.contextPath}/images/welcome.png" alt="Привет" style="padding: 30px" height="500">
                </p>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>