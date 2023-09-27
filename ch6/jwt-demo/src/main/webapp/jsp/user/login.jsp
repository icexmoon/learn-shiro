<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/18
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>请登录</h2>
<c:if test="${errorMsg!=null}">
    <h3>${errorMsg}</h3>
</c:if>
<form method="post" action="/user/login">
    <table>
        <tr>
            <td>username:</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="login">
            </td>
        </tr>
    </table>
</form>
</form>
</body>
</html>
