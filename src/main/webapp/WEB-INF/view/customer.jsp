<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>客户管理</title>
</head>
<body>
<h1>客户列表</h1>
<a href="${BASE}/customer_create">创建</a>
<table>
    <tr>
        <th>客户名称</th>
        <th>联系人</th>
        <th>电话号码</th>
        <th>邮箱地址</th>
        <th>操作</th>
    </tr>
    <c:forEach var="customer" items="${list}">
    <tr>
        <th>${customer.name}</th>
        <th>${customer.contact}</th>
        <th>${customer.mobile}</th>
        <th>${customer.email}</th>
        <th>
            <a href="${BASE}/customer_edit?id=${customer.id}">编辑</a>
            <a href="${BASE}/customer_delete?id=${customer.id}">删除</a>
        </th>
    </tr>
    </c:forEach>
</table>
</body>
</html>