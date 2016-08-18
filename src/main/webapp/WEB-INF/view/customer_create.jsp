<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>客户创建</title>
</head>
<body>
<h1>客户创建</h1>

<h2>${message}</h2>
<form action="${BASE}/customer_create" method="post">
<table>
    <tr>
        <td>客户名称</td>
        <td><input name="name"/></td>
    </tr>
    <tr>
        <td>联系人</td>
        <td><input name="contact" /></td>
    </tr>
        <td>电话号码</td>
        <td><input name="mobile" /></td>
    </tr>
        <td>邮箱地址</td>
        <td><input name="email" /></td>
    </tr>
     </tr>
        <td>备注</td>
        <td><input name="remark" /></td>
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