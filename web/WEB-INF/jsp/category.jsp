<%--
  Created by a.shestovsky
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Catalog</title>
        <style>
            <%@include file="../css/category.css"%>
            <%@include file="../css/header.css"%>
            <%@include file="../css/footer.css"%>
            <%@include file="../css/reset.css"%>
        </style>
        <%--<script type="text/javascript">--%>
            <%--<%@include file="../js/bootstrap.js"%>--%>
        <%--</script>--%>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <%@include file="category-tree.jsp"%>
                <div class="parent-categories">
                    <div>
                        <c:forEach var="product" items="${requestScope.products}">
                            <div class="product-item">
                                <a href="${pageContext.request.contextPath}/product?id=${product.id}">
                                <img class="product-image" src="${product.imageURL}">
                                <p class="product-name">${product.name}</p>
                                <div class="short-description">
                                    <p>
                                        ${product.description}
                                    </p>
                                </div>
                                <p class="product-price">
                                    ${product.price} руб.
                                </p>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>