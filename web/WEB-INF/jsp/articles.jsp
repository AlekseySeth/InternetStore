<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Articles</title>
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
            <h1 class="main-header">Статьи</h1>
            <p style="padding: 0 0 10px 20px; font-size: 18px">Данный раздел находится в разработке</p>
            <img src="${pageContext.request.contextPath}/images/wip.png" alt="Страница в разработке" style="padding: 30px">
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>