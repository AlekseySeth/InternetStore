<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Update</title>
        <style>
            <%@include file="../css/main.css"%>
            <%@include file="../css/header.css"%>
            <%@include file="../css/footer.css"%>
            <%@include file="../css/reset.css"%>
        </style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <div class="password-change-container">
                    <h2 id="password-update">Смена пароля</h2>
                    <form action="${pageContext.request.contextPath}/update-password" method="post">
                        <input type="password" id="newPassword" name="newPassword" placeholder="новый пароль" required>
                        <input type="password" id="confirmNewPassword" name="confirmNewPassword" placeholder="подтвердите новый пароль" required>
                        <button class="profile-button" type="submit" onclick="comparePasswords()">Изменить пароль</button>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>