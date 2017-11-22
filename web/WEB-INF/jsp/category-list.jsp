<%--
  Created by a.shestovsky
--%>
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
</head>
    <body>
        <div class="wrapper">

            <%@include file="header.jsp"%>

            <div class="main">
                <%@include file="category-tree.jsp"%>

                <div class="parent-categories">

                </div>




            </div>

        <%@include file="footer.jsp"%>

    </body>
</html>
