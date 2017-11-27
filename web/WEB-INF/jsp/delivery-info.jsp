<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery Information</title>
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
                <h1>Информация о доставке</h1>
                <p>Самовывоз - вы можете забрать заказ в одном из наших магазинов в городе Минске.</p>
                <p>Стандартная доставка - осуществляется курьером по Минску. Если заказ размещен до 12.00, то доставка
                будет произведена на следующий день. Если заказ размещен после 12.00, то через день. Более точное время
                оговаривается с оператором.</p>
                <p>Экспресс доставка - доставка по Минску осуществляется в день заказа.</p>
            </div>
        <%@include file="footer.jsp"%>
    </body>
</html>