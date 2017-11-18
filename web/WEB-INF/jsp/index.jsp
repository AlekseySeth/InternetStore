<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta charset="utf-8">
    <title>Sports nutrition store</title>
      <style>
      <%@include file="../css/main.css"%>
      <%@include file="../css/reset.css"%>
      </style>

  </head>
  <body>
  <div class="wrapper">
      <!-- Header -->
      <%@include file="header.jsp"%>

      <div class="main">

          <article class="article-main">
              <h1>Приветствие</h1>
              <p>
                  Текст приветствия
              </p>
          </article>

          <div class="categories-main">
              <h2>Категории товаров</h2>
              <p>Табличка с категориями товаров</p>
          </div>

      </div>

      <%@include file="footer.jsp"%>

  </body>
</html>