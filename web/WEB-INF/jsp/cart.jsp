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
                <div class="top">
                    <h2>Ваш заказ</h2>
                </div>
                <div class="cart">
                    <c:forEach var="product" items="${sessionScope.order.products}">
                        <p>${product.name}</p>
                        <p>${product.price}</p>
                    </c:forEach>
                </div>

                <div class="delivery">
                    <h3>Выберите способ доставки</h3>
                    <form action="${pageContext.request.contextPath}/cart" method="post">
                    <c:forEach var="delivery" items="${requestScope.deliveries}">
                        <input type="radio" name="delivery" value="${delivery.id}" onchange="submit()">${delivery.name}
                    </c:forEach>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>