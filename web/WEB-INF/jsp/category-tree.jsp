<%--
  Created by a.shestovsky
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside>
    <div class="catalog-tree">
        <h2>Категории товаров</h2>
        <ul>
            <c:forEach var="category" items="${requestScope.categories}">
                <li><a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</aside>