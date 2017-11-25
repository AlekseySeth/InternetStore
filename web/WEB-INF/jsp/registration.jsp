<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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
            <div class="registration-form">
                <h2>Вход</h2>
                <form action="${pageContext.request.contextPath}/registration" method="post">
                    <p>
                        <label for="firstName">Имя </label>
                        <input type="text" id="firstName" name="firstName">
                    </p>
                    <p>
                        <label for="lastName">Фамилия </label>
                        <input type="text" id="lastName" name="lastName">
                    </p>
                    <p>
                    <label for="email">E-mail </label>
                    <input type="text" id="email" name="email">
                    </p>
                    <p>
                    <label for="password">Пароль </label>
                    <input type="password" id="password" name="password">
                    </p>
                    <p>
                    <label for="repPassword">Повторите пароль </label>
                    <input type="password" id="repPassword" name="repPassword">
                    </p>
                    <p>
                    <label for="phone">Телефон </label>
                    <input type="text" id="phone" name="phone">
                    </p>
                    <p>
                    <label for="address">Адрес  </label>
                    <input type="text" id="address" name="address">
                    </p>
                    <button class="registration-button" type="submit">Зарегистрироваться</button>
                    <a href="${pageContext.request.contextPath}/login">Вход в аккаунт</a>
                </form>
            </div>


        </div>

        <%@include file="footer.jsp"%>

    </body>
</html>