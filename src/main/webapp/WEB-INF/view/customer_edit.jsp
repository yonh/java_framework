<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>客户编辑</title>
</head>
<body>
<h1>客户编辑</h1>

<h2>${message}</h2>

<form action="${BASE}/customer_edit" method="post">
<input name="id" type="hidden" value="${customer.id}"/>
<table>
    <tr>
        <td>客户名称</td>
        <td><input name="name" value="${customer.name}"/></td>
    </tr>
    <tr>
        <td>联系人</td>
        <td><input name="contact" value="${customer.contact}"/></td>
    </tr>
        <td>电话号码</td>
        <td><input name="mobile" value="${customer.mobile}" /></td>
    </tr>
        <td>邮箱地址</td>
        <td><input name="email" value="${customer.email}" /></td>
    </tr>
     </tr>
        <td>备注</td>
        <td><input name="remark" value="${customer.remark}"/></td>
    </tr>
        <td></td>
        <td>
            <button>保存</button>
            <a href="${BASE}/customer">返回</a>
        </td>
    </tr>
</table>
</form>
</body>
</html>