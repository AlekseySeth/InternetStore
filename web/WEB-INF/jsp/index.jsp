<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Sports nutrition store</title>
        <style>
        <%@include file="../css/main.css"%>
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
                <article class="article-main">
                    <h1>Приветствие</h1>
                    <img src="${pageContext.request.contextPath}/images/default.gif" width="100" height="100">
                    <p>
                    Текст приветствия
                    </p>
                </article>
                <div class="categories-main">
                    <h2>Категории товаров</h2>
                    <p>Табличка с категориями товаров</p>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>