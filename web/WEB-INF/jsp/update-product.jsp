<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>User Information</title>
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
                <%@include file="admin-left-navigation-bar.jsp"%>
                <div class="update-product-container">
                    <h2 id="prod-update">Изменение продукта</h2>
                    <form action="${pageContext.request.contextPath}/update-product">
                    <table id="update-product">
                        <tr>
                            <td>ID</td><td>${requestScope.product.id}</td>
                        </tr>
                        <tr>
                            <td>Наименование</td><td><input type="text" name="name" value="${requestScope.product.name}"></td>
                        </tr>
                        <tr>
                            <td style="vertical-align: middle">Описание</td><td><textarea name="description" cols="60" rows="5">${requestScope.product.description}</textarea></td>
                        </tr>
                        <tr>
                            <td>Стоимость</td><td><input type="text" name="price" value="${requestScope.product.price}"></td>
                        </tr>
                        <tr>
                            <td>На складе</td><td><input type="number" name="qtyInStock" value="${requestScope.product.qtyInStock}"></td>
                        </tr>
                        <tr>
                            <td>Категория</td><td>${requestScope.product.category.getCategory().name}</td>
                        </tr>
                        <tr>
                            <td>Подкатегория</td><td>${requestScope.product.category.name}</td>
                        </tr>
                    </table>
                        <button id="pu-button" type="submit">Принять изменения</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/delete-product">
                        <input type="hidden" value="${requestScope.product.id}">
                        <button id="pu-button-delete" type="submit">Удалить продукт</button>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>