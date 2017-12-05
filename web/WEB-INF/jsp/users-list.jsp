<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Users List</title>
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
                <div class="users-list">
                    <table id="users">
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>E-mail</th>
                        <th>Зарегистрирован</th>
                        <th>Телефон</th>
                        <th>Адрес</th>
                    <c:forEach var="userItem" items="${requestScope.users}">
                        <tr>
                            <td>${userItem.id}</td>
                            <td>${userItem.firstName}</td>
                            <td>${userItem.lastName}</td>
                            <td>${userItem.email}</td>
                            <td>${userItem.registrationDate}</td>
                            <td>${userItem.phone}</td>
                            <td>${userItem.address}</td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>