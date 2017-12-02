<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>User Information</title>
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
                <div class="left-navigation-bar">
                    <form action="${pageContext.request.contextPath}/users-list">
                        <button class="lnb-button" type="submit">Список пользователей</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/user">
                        <input type="text" name="userId" placeholder="введите ID пользователя">
                        <button class="find" type="submit">Найти</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/user">
                        <input type="text" name="userEmail" placeholder="введите e-mail пользователя">
                        <button class="find" type="submit">Найти</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/products-list">
                        <button class="lnb-button" type="submit">Список продуктов</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/update-product">
                        <input type="text" placeholder="введите ID продукта">
                        <button class="find" type="submit">Найти</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/orders-list">
                        <button class="lnb-button" type="submit">Список заказов</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/update-order">
                        <input type="text" placeholder="введите ID заказа">
                        <button class="find" type="submit">Найти</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/log-out" class="log-out" method="post">
                        <input type="hidden" name="logOut" value="true">
                        <button class="lnb-button" type="submit">Выйти из аккаунта</button>
                    </form>
                </div>
                <div class="user-change-container">
                    <h2 id="profile-update">Изменение данных пользователя</h2>
                    <form action="${pageContext.request.contextPath}/update-user" method="post">
                        <div>
                            <label for="firstName"><fmt:message key="registration.first.name"/></label>
                            <input type="text" id="firstName" name="firstName" placeholder=" " value="${requestScope.user.firstName}">
                        </div>
                        <div>
                            <label for="lastName"><fmt:message key="registration.last.name"/></label>
                            <input type="text" id="lastName" name="lastName" placeholder=" " value="${requestScope.user.lastName}">
                        </div>
                        <div>
                            <label for="phone"><fmt:message key="registration.phone"/></label>
                            <input type="tel" id="phone" name="phone" placeholder=" " value="${requestScope.user.phone}">
                        </div>
                        <div>
                            <label for="address"><fmt:message key="registration.address"/></label>
                            <input type="text" id="address" name="address" placeholder=" " value="${requestScope.user.address}">
                        </div>
                        <div>
                            <label for="newPassword">Новый пароль</label>
                            <p><input type="password" id="newPassword" name="newPassword" placeholder="новый пароль"></p>
                            <label for="confirmNewPassword">Подтвердите новый пароль</label>
                            <p><input type="password" id="confirmNewPassword" name="confirmNewPassword" placeholder="подтвердите новый пароль"></p>
                        </div>
                        <button type="submit">Изменить данные</button>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>