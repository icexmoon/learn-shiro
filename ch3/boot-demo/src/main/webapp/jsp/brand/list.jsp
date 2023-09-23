<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/18
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>品牌列表</h2>
<h3>导航栏 <a href="/user/home">个人主页</a> <a href="/brand/list">品牌列表页</a> <a href="/user/logout">注销</a></h3>
<shiro:hasPermission name="brand:add">
    <a href="/jsp/brand/add.jsp">新增</a>
</shiro:hasPermission>
<table>
    <tr>
        <td>序号</td>
        <td>品牌名</td>
        <td>公司名</td>
        <td>排序</td>
        <td>公司介绍</td>
        <td>状态</td>
    </tr>
    <c:forEach items="${brands}" var="brand">
        <tr>
            <td>${brand.id}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.companyDesc}</td>
            <td>
                <c:if test="${brand.status==0}">启用</c:if>
                <c:if test="${brand.status==1}">禁用</c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
