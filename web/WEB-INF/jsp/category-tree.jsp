<%--
  Created by a.shestovsky
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<aside>
    <div class="category-tree">
        <h2><fmt:message key="catalog.tree.header"/></h2>
        <ul>
            <c:forEach var="category" items="${requestScope.categories}">
                <li class="tree-element"><a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</aside>