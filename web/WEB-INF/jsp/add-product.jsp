<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Product</title>
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
        <c:if test="${sessionScope.user.role eq 'MARKETER'}">
            <%@include file="marketer-left-navigation-bar.jsp"%>
        </c:if>
        <div class="update-product-container">
            <h2 id="prod-add">Добавление продукта</h2>
            <form action="${pageContext.request.contextPath}/add-product" method="post">
                <table id="update-product">
                    <tr>
                        <td>Наименование</td><td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle">Описание</td><td><textarea name="description" cols="60" rows="5"></textarea></td>
                    </tr>
                    <tr>
                        <td>Стоимость</td><td><input type="text" name="price"></td>
                    </tr>
                    <tr>
                        <td>На складе</td><td><input type="number" name="qtyInStock"></td>
                    </tr>
                    <tr>
                        <td>Изображение</td><td><input type="text" name="imageURL""></td>
                    </tr>
                    <tr>
                        <td>Категория</td><td>${requestScope.product.category.getCategory().name}</td>
                    </tr>
                    <tr>
                        <td>Подкатегория</td><td>${requestScope.product.category.name}</td>
                    </tr>
                </table>
                <input type="hidden" name="productId" value="${requestScope.product.id}">
                <button id="pu-button" type="submit">Добавить продукт</button>
            </form>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</div>
</body>
</html>