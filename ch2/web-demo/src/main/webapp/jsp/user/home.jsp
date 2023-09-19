<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/19
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>个人主页</h2>
<h3>导航栏 <a href="/user/home">个人主页</a>
    <shiro:authenticated>
        <shiro:hasAnyRoles name="sys_manager,dep_manager">
            <shiro:hasPermission name="brand:view">
                <a href="/brand/list">品牌列表页</a>
            </shiro:hasPermission>
        </shiro:hasAnyRoles>
    </shiro:authenticated>
    <a href="/user/logout">注销</a></h3>
欢迎登录，${username}
</body>
</html>
