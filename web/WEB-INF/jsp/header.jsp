<%--
  Created by a.shestovsky
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="translations"/>
<header>
    <div class="title">
        <a id="title" href="${pageContext.request.contextPath}/">
        <p class="store-title">Sport Pit</p>
        </a>
    </div>
</header>

<nav>
    <ul>
        <li><a class="navigation" href="${pageContext.request.contextPath}/category-list"><fmt:message key="header.catalog"/></a></li>
        <li><a class="navigation" href="${pageContext.request.contextPath}/articles"><fmt:message key="header.articles"/></a></li>
        <li><a class="navigation" href="${pageContext.request.contextPath}/delivery-info"><fmt:message key="header.delivery"/></a></li>
        <li><a class="navigation" href="${pageContext.request.contextPath}/contact-us"><fmt:message key="header.contact"/></a></li>
        <li class="my-account"><a class="navigation" href="${pageContext.request.contextPath}/cart"><fmt:message key="header.cart"/></a></li>
        <li class="my-account"><a class="navigation" href="${pageContext.request.contextPath}/my-account"><fmt:message key="header.account"/></a></li>
    </ul>
</nav>