<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Update</title>
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
                <div class="profile-change-container">
                    <h2 id="profile-update">Изменение персональных данных</h2>
                    <form action="${pageContext.request.contextPath}/update-profile" method="post">
                        <div class="profile-update-element">
                            <label for="firstName"><fmt:message key="registration.first.name"/></label>
                            <input type="text" id="firstName" name="firstName" placeholder=" " value="${sessionScope.user.firstName}">
                        </div>
                        <div class="profile-update-element">
                            <label for="lastName"><fmt:message key="registration.last.name"/></label>
                            <input type="text" id="lastName" name="lastName" placeholder=" " value="${sessionScope.user.lastName}">
                        </div>
                        <div class="profile-update-element">
                            <label for="phone"><fmt:message key="registration.phone"/></label>
                            <input type="tel" id="phone" name="phone" placeholder=" " value="${sessionScope.user.phone}">
                        </div>
                        <div class="profile-update-element">
                            <label for="address"><fmt:message key="registration.address"/></label>
                            <input type="text" id="address" name="address" placeholder=" " value="${sessionScope.user.address}">
                        </div>
                        <button class="profile-button" type="submit">Изменить данные</button>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>