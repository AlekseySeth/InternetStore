<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Order</title>
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
                <div class="order-info-container">
                    <table class="order-info">
                        <tr><td class="oi_title">Номер заказа</td><td class="oi_value">${orderInfo.id}</td></tr>
                        <tr><td class="oi_title">Статус</td><td class="oi_value">${orderInfo.status.getAsString()}</td></tr>
                        <tr><td class="oi_title">Сумма заказа</td><td class="oi_value">${orderInfo.totalPrice}</td></tr>
                        <tr><td class="oi_title">Способ доставки</td><td class="oi_value">${orderInfo.getDelivery().name}</td></tr>
                        <tr><td class="oi_title">Дата размещения</td><td class="oi_value">${orderInfo.openDate}</td></tr>
                        <tr><td class="oi_title">Дата закрытия</td><td class="oi_value">${orderInfo.closeDate}</td></tr>
                    </table>
                </div>
                <div class="order-info-products-container">
                    <table class="order-info-products">
                        <tr>
                            <th id="head-name">Наименование продукта</th>
                            <th class="price">Цена</th>
                            <th>Количество</th>
                        </tr>
                        <c:forEach var="product" items="${requestScope.orderInfo.products}">
                            <tr><td id="name">${product.key.name}</td><td class="price">${product.key.price} руб.</td><td>${product.value}</td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>