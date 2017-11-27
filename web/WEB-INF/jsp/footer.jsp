<%--
  Created by a.shestovsky
--%>
<footer>
    <p id="footer">&copy; Aleksey Shestovsky, 2017
        <form class="language-selector" action="${pageContext.request.contextPath}/language" method="post">
            <button name="language" type="submit" value="ru_RU">RU</button>
            <button name="language" type="submit" value="en_US">EN</button>
        </form>
    </p>
</footer>