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
                <div class="user-container">
                    <table class="user">
                        <tr><td class="user-row-title">ID</td><td class="user-row-value">${requestScope.foundUser.id}</td></tr>
                        <tr><td class="user-row-title"><fmt:message key="registration.first.name"/></td><td class="user-row-value">${requestScope.foundUser.firstName}</td></tr>
                        <tr><td class="user-row-title"><fmt:message key="registration.last.name"/></td><td class="user-row-value">${requestScope.foundUser.lastName}</td></tr>
                        <tr><td class="user-row-title">E-mail</td><td class="user-row-value">${requestScope.foundUser.email}</td></tr>
                        <tr><td class="user-row-title"><fmt:message key="registration.phone"/></td><td class="user-row-value">${requestScope.foundUser.phone}</td></tr>
                        <tr><td class="user-row-title"><fmt:message key="registration.address"/></td><td class="user-row-value">${requestScope.foundUser.address}</td></tr>
                        <tr><td class="user-row-title">Дата регистрации</td><td class="user-row-value">${requestScope.foundUser.registrationDate}</td></tr>
                    </table>
                    <h2 id="user-orders-list">Список заказов</h2>
                    <table class="user-my-orders">
                        <th>Номер</th>
                        <th>Статус</th>
                        <th>Сумма</th>
                        <th>Дата размещения</th>
                        <th>Дата закрытия</th>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td class="id">${order.id}</td>
                                <td>${order.status}</td>
                                <td class="sum">${order.totalPrice}</td>
                                <td id="open-date">${order.openDate}</td>
                                <td id="close-date">${order.closeDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>