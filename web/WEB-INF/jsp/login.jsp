<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="../css/main.css"%>
        <%@include file="../css/reset.css"%>
        <%@include file="../css/header.css"%>
        <%@include file="../css/footer.css"%>
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
    <body>
    <%--<div class="b-popup" id="popup">--%>
        <%--<div class="b-popup-content">--%>
            <%--Ошибка! Введены некорректные данные--%>
            <%--<a href="javascript:PopUpHide()">Понятно</a>--%>
        <%--</div>--%>
    </div>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <div id="login" class="login-form">
                    <h2><fmt:message key="login"/></h2>
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <p><input type="text" id="email" name="email" placeholder="email" required></p>
                        <p><input type="password" id="password" name="password" placeholder="<fmt:message key="login.password"/>" required></p>
                        <button class="login-button" type="submit"><fmt:message key="login.button"/></button>
                        <p class="registration-link"><a href="${pageContext.request.contextPath}/registration"><fmt:message key="login.registration.link"/></a></p>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>