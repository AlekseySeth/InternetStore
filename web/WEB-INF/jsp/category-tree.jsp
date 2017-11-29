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
                <li class="tree-element">
                	<a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a>
                </li>
                <ul>
                	<c:forEach var="child" items="${requestScope.subCategories}">
                		<li class="child-element">
                			<c:if test="${category.id eq child.category.id}">
                				<a href="${pageContext.request.contextPath}/category?id=${child.id}">${child.name}</a>
                			</c:if>
                		</li>
                	</c:forEach>
                </ul>
            </c:forEach>
        </ul>
    </div>
</aside>