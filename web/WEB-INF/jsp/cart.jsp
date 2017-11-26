<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="top">
                    <h2>Ваш заказ</h2>
                </div>
                <div class="cart">
                    <p>${sessionScope.order.id}</p>
                    <p>${sessionScope.order.totalPrice}</p>
                </div>
                <div class="delivery">
                    <h3>Выберите способ доставки</h3>
                    <input type="radio" name="delivery" value="">
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>