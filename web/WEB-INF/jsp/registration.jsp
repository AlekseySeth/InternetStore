<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registration</title>
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
                <div id="registration" class="registration-form">
                    <h2><fmt:message key="registration"/></h2>
                    <form action="${pageContext.request.contextPath}/registration" method="post">
                        <div>
                            <label for="firstName"><fmt:message key="registration.first.name"/></label>
                            <input type="text" id="firstName" name="firstName" required>
                        </div>
                        <div>
                            <label for="lastName"><fmt:message key="registration.last.name"/></label>
                            <input type="text" id="lastName" name="lastName">
                        </div>
                        <div>
                            <label for="email">E-mail </label>
                            <input type="email" id="email" name="email" required>
                            <span class="form-error">Это поле должно содержать E-mail в формате example@site.com</span>
                        </div>
                        <div>
                            <label for="password"><fmt:message key="registration.password"/></label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <div>
                            <label for="repPassword"><fmt:message key="registration.repeat.password"/></label>
                            <input type="password" id="repPassword" name="repPassword" required>
                        </div>
                        <div>
                            <label for="phone"><fmt:message key="registration.phone"/></label>
                            <input type="tel" id="phone" name="phone" required>
                        </div>
                        <div>
                            <label for="address"><fmt:message key="registration.address"/></label>
                            <input type="text" id="address" name="address" required>
                        </div>
                            <button class="registration-button" type="submit"><fmt:message key="registration.button"/></button>
                            <p class="login-link"><a  href="${pageContext.request.contextPath}/login"><fmt:message key="registration.login.link"/></a></p>
                    </form>
                </div>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>