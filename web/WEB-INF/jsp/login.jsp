<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="../css/main.css"%>
        <%@include file="../css/reset.css"%>
        <%@include file="../css/header.css"%>
        <%@include file="../css/footer.css"%>
    </style>
    <%--<script type="text/javascript">--%>
        <%--<%@include file="../js/bootstrap.js"%>--%>
    <%--</script>--%>
</head>
    <body>
    <div class="wrapper">
        <!-- Header -->
        <%@include file="header.jsp"%>

        <div class="main">
            <div id="login" class="login-form">
                <h2>Вход</h2>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <p><input type="text" id="email" name="email" placeholder="email"></p>
                    <p><input type="password" id="password" name="password" placeholder="пароль"></p>
                    <button class="login-button" type="submit">Войти</button>
                    <p class="registration-link"><a href="${pageContext.request.contextPath}/registration">Регистрация</a></p>
                </form>
            </div>
        </div>

        <%@include file="footer.jsp"%>

    </body>
</html>