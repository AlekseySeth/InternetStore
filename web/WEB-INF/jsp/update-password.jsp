<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Cart</title>
        <style>
            <%@include file="../css/main.css"%>
            <%@include file="../css/header.css"%>
            <%@include file="../css/footer.css"%>
            <%@include file="../css/reset.css"%>
        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="header.jsp"%>
            <div class="main">
                <div class="password-change-container">ы
                    <h2 id="password-update">Смена пароля</h2>
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <p><input type="password" id="newPassword" name="newPassword" placeholder="новый пароль"></p>
                        <p><input type="password" id="confirmNewPassword" name="confirmNewPassword" placeholder="подтвердите новый пароль"></p>
                        <button type="submit">Изменить пароль</button>
                        <c:if test="${requestScope.message eq 'success'}">
                            <p class="password-message" style="color: green">Пароль успешно изменен</p>
                        </c:if>
                        <c:if test="${requestScope.message eq 'failed'}">
                            <p class="password-message" style="color: red">Введены некорректные данные. Попробуйте снова</p>
                        </c:if>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>