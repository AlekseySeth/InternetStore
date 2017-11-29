<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marketer Account</title>
    <style>
        <%@include file="../css/main.css"%>
        <%@include file="../css/reset.css"%>
        <%@include file="../css/header.css"%>
        <%@include file="../css/footer.css"%>
    </style>
    <%--<script type="text/javascript">--%>
        <%--<%@include file="../js/bootstrap.js"%>--%>
    <%--</script>--%>
</head>
    <body>
    <div class="wrapper">
        <%@include file="header.jsp"%>
        <div class="main">
            <form action="/products-list" method="get">
                <input type="hidden" name="orderId" value="${order.id}">
                <button type="submit">Список продуктов</button>
            </form>
            <form action="/orders-list" method="get">
                <input type="hidden" name="orderId" value="${order.id}">
                <button type="submit">Список заказов</button>
            </form>
            <form action="/log-out" class="log-out" method="post">
                <input type="hidden" name="logOut" value="true">
                <button type="submit">Выйти из аккаунта</button>
            </form>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>