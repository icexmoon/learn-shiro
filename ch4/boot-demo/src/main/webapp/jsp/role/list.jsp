<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/22
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>角色列表</h2>
<table>
    <tr>
        <td>id</td>
        <td>名称</td>
        <td>说明</td>
    </tr>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
            <td>${role.description}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
