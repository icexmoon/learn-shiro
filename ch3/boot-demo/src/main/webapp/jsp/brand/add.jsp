<%--
  Created by IntelliJ IDEA.
  User: 70748
  Date: 2023/9/18
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>新增品牌</h2>
<form method="post" action="/brand/add">
    <table>
        <tr>
            <td>品牌名称</td>
            <td><input type="text" name="brandName"/></td>
        </tr>
        <tr>
            <td>公司名称</td>
            <td><input type="text" name="companyName"/></td>
        </tr>
        <tr>
            <td>排序</td>
            <td><input type="text" name="ordered"/></td>
        </tr>
        <tr>
            <td>公司介绍</td>
            <td><textarea name="companyDesc"></textarea></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                启用<input type="radio" value="0" name="status">
                禁用<input type="radio" value="1" name="status">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
