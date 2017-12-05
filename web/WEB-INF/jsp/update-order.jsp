<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Update Order</title>
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
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <%@include file="admin-left-navigation-bar.jsp"%>
                </c:if>
                <c:if test="${sessionScope.user.role eq 'MARKETER'}">
                    <%@include file="marketer-left-navigation-bar.jsp"%>
                </c:if>
                <div class="update-order-container">
                    <h2 id="order-update">Изменение заказа</h2>
                    <form action="${pageContext.request.contextPath}/update-order" method="post">
                    <table id="update-order">
                        <tr><td>ID</td><td>${requestScope.order.id}</td></tr>
                        <tr><td>Статус</td>
                            <td><select name="status">
                                <c:forEach var="statusItem" items="${requestScope.statuses}">
                                    <option value="${statusItem}" ${statusItem eq requestScope.order.status ? 'selected' : ''}>${statusItem.getAsString()}</option>
                                </c:forEach>
                            </select></td>
                        </tr>
                        <tr><td>Сумма</td><td>${requestScope.order.totalPrice}</td></tr>
                        <tr><td>Доставка</td><td>${requestScope.order.getDelivery().name}</td></tr>
                        <tr><td>Дата размещения</td><td>${requestScope.order.openDate}</td></tr>
                        <tr><td>Дата закрытия</td><td>${requestScope.order.closeDate}</td></tr>
                        <tr><td>Пользователь</td><td>${requestScope.order.getUser().email}</td></tr>
                    </table>
                        <input type="hidden" name="orderId" value="${requestScope.order.id}">
                        <button id="change-status" type="submit">Изменить статус</button>
                    </form>
                    <h2 id="uo-products">Продукты в заказе</h2>
                    <table id="update-order-products">
                        <th style="text-align: left">Наименование</th>
                        <th>Цена</th>
                        <th>Количество</th>
                        <c:forEach var="product" items="${requestScope.order.products}">
                            <tr><td style="text-align: left">${product.key.name}</td><td>${product.key.price} руб.</td><td>${product.value}</td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>