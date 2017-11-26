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
                    <div class="qty">
                        <form action="${pageContext.request.contextPath}/cart">
                            <label for="qty">Количество</label>
                            <input id="qty" type="number" name="qty" value="1">
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>
