<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Products List</title>
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
                <div class="products-list-container">
                    <table id="products-list">
                        <th>ID</th>
                        <th>Наименование</th>
                        <th>Стоимость</th>
                        <th>На складе</th>
                        <th>Категория</th>
                        <th>Дочерняя категория</th>
                        <c:forEach var="product" items="${requestScope.products}">
                            <tr>
                                <td>${product.id}</td>
                                <td id="pl-product-name">${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.qtyInStock}</td>
                                <td>${product.category.getCategory().name}</td>
                                <td>${product.category.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>