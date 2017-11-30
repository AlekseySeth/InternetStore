<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Account</title>
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
        <%@include file="header.jsp"%>
        <div class="main">
            <h2>Добро пожаловать, ${sessionScope.user.firstName} ${sessionScope.user.lastName}</h2>
            <form action="${pageContext.request.contextPath}/log-out" class="log-out" method="post">
                <input type="hidden" name="logOut" value="true">
                <button type="submit">Выйти из аккаунта</button>
            </form>
            <h2 id="profile-header">Персональные данные</h2>
            <table class="profile">
                <tr><td><fmt:message key="registration.first.name"/></td><td>${sessionScope.user.firstName}</td></tr>
                <tr><td><fmt:message key="registration.last.name"/></td><td>${sessionScope.user.lastName}</td></tr>
                <tr><td>E-mail</td><td>${sessionScope.user.email}</td></tr>
                <tr><td><fmt:message key="registration.phone"/></td><td>${sessionScope.user.phone}</td></tr>
                <tr><td><fmt:message key="registration.address"/></td><td>${sessionScope.user.address}</td></tr>
            </table>
            <form action="${pageContext.request.contextPath}/update-profile" class="update-profile" method="post">
                <input type="hidden" name="userId" value="${sessionScope.user.id}">
                <button type="submit">Редактировать данные</button>
            </form>
            <form action="${pageContext.request.contextPath}/update-password" class="update-password" method="post">
                <input type="hidden" name="userId" value="${sessionScope.user.id}">
                <button type="submit">Изменить пароль</button>
            </form>
            <h2 id="orders-header">Мои заказы</h2>
            <table class="my-orders">
                <th>Номер</th>
                <th>Статус</th>
                <th>Дата размещения</th>
                <th>Сумма</th>
                <th></th>
                <th></th>
                <c:forEach var="order" items="${requestScope.orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.status}</td>
                            <td>${order.openDate}</td>
                            <td>${order.totalPrice}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/order?id=${order.id}" method="get">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <button type="submit">Открыть заказ</button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/download-order" method="post">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <button type="submit">Скачать файлом</button>
                                </form>
                            </td>
                        </tr>
                </c:forEach>
            </table>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>