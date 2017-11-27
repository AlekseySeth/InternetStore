<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product Info</title>
        <style>
            <%@include file="../css/product.css"%>
            <%@include file="../css/header.css"%>
            <%@include file="../css/footer.css"%>
            <%@include file="../css/reset.css"%>
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <%@include file="category-tree.jsp"%>
                <div class="top"><h2>${requestScope.product.name}</h2></div>
                <div class="product">
                    <div id="image">
                        <img src="${requestScope.product.imageURL}" width="200" height="200">
                    </div>
                    <div class="add-to-cart">
                        <p class="price">${requestScope.product.price} руб.</p>
                        <form action="${pageContext.request.contextPath}/product" method="post">
                            <input type="hidden" name="id" value="${requestScope.product.id}">
                            <label for="qty">Количество</label>
                            <input id="qty" type="number" name="qty" value="1">
                            <button type="submit">Добавить в корзину</button>
                        </form>
                        <c:if test="${requestScope.product.qtyInSock gt 0}">
                            <p class="stock" style="color: green">В наличии</p>
                        </c:if>
                        <c:if test="${requestScope.product.qtyInSock eq 0}">
                            <p class="stock" style="color: red">Нет на складе</p>
                        </c:if>
                    </div>
                    <div class="full-description">
                        <p>${requestScope.product.description}</p>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>
