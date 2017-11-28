<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Cart</title>
        <style>
            <%@include file="../css/cart.css"%>
            <%@include file="../css/header.css"%>
            <%@include file="../css/footer.css"%>
            <%@include file="../css/reset.css"%>
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <div class="cart-container">
                    <div class="top">
                        <h2>Ваш заказ</h2>
                    </div>
                    <table class="cart">
                        <tr>
                            <th id="head-name">Наименование продукта</th>
                            <th>Цена</th>
                            <th>Количество</th>
                        </tr>
                        <c:forEach var="product" items="${sessionScope.order.products}">
                            <tr><td id="name">${product.key.name}</td><td>${product.key.price} руб.</td><td>${product.value}</td></tr>
                        </c:forEach>
                    </table>
                    <div class="subtotal">
                        Сумма ${requestScope.subtotalPrice} руб.
                    </div>
                    <h3 class="delivery-header">Выберите способ доставки</h3>
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <table class="delivery">
                        <c:forEach var="delivery" items="${requestScope.deliveries}">
                            <tr><td><input id="${delivery.id}" type="radio" name="delivery" value="${delivery.id}" ${sessionScope.order.delivery.id eq delivery.id ? "checked" : ""} onchange="submit()"><label for="${delivery.id}">${delivery.name}</label></td>
                                <td>${delivery.cost}</td>
                                <td>руб.</td></tr>
                        </c:forEach>
                        </form>
                    </table>
                    <div class="subtotal">
                        Итого ${sessionScope.order.totalPrice} руб.
                    </div>
                    <form action="${pageContext.request.contextPath}/order-placed" method="post">
                        <input type="hidden" name="isPlaced" value="true">
                        <button class="place-order" type="submit">Разместить заказ</button>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>