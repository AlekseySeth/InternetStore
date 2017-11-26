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
                        <img src="${requestScope.product.imageURL}">
                    </div>
                    <div class="add-to-cart">
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <label for="qty">Количество</label>
                            <input id="qty" type="number" name="qty" value="1">
                            <input type="hidden" name="productId" value="${requestScope.product.id}">
                            <button type="submit">Добавить в корзину</button>
                        </form>
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
