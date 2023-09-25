<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/22
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${errorMsg!=null}">
    <h2>${errorMsg}</h2>
</c:if>
<a href="/user/home">返回个人主页</a>
</body>
</html>
