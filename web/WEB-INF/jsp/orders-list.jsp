<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Orders List</title>
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
                <div class="orders-list-container">
                    <table id="orders-list">
                        <th>ID</th>
                        <th>Статус</th>
                        <th>Доставка</th>
                        <th>Сумма</th>
                        <th>Дата размещения</th>
                        <th>Дата закрытия</th>
                        <th>Пользователь</th>
                        <c:forEach var="orderItem" items="${requestScope.orders}">
                            <tr>
                                <td>${orderItem.id}</td>
                                <td>${orderItem.status}</td>
                                <td id="delivery">${orderItem.delivery}</td>
                                <td>${orderItem.totalPrice}</td>
                                <td>${orderItem.openDate}</td>
                                <td>${orderItem.closeDate}</td>
                                <td>${orderItem.userEmail}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

            </div>
            <%@include file="footer.jsp"%>
        </div>
    </body>
</html>