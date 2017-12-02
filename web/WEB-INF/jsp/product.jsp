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
                <div class="top"><h2>${requestScope.productPage.name}</h2></div>
                <div class="product">
                    <div id="image">
                        <img src="${requestScope.productPage.imageURL}" width="200" height="200">
                    </div>
                    <div class="add-to-cart">
                        <p class="price">${requestScope.productPage.price} руб.</p>
                        <form action="${pageContext.request.contextPath}/product" method="post">
                            <input type="hidden" name="id" value="${requestScope.productPage.id}">
                            <label for="qty">Количество</label>
                            <input id="qty" type="number" name="qty" value="1">
                            <button type="submit">Добавить в корзину</button>
                        </form>
                        <c:if test="${requestScope.productPage.qtyInStock gt 0}">
                            <p class="stock" style="color: green">В наличии</p>
                        </c:if>
                        <c:if test="${requestScope.productPage.qtyInStock eq 0}">
                            <p class="stock" style="color: red">Нет на складе</p>
                        </c:if>
                    </div>
                    <div class="full-description">
                        <p>${requestScope.productPage.description}</p>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>
