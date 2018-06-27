<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
        <th>create date</th>
        <th>role</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.password}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${date.format(user.createDate)}"></c:out></td>
        <td><c:out value="${user.role.role}"></c:out></td>
        <th>
            <form action="${pageContext.servletContext.contextPath}/admin/updateAdmin" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="update"/>
            </form>
        </th>
        <th>
            <form action="${pageContext.servletContext.contextPath}/admin" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" name="action" value="delete"/>
            </form>
        </th>
    </tr>
</c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/admin/addAdmin" method="get">
    <input type="submit" value="add"/>
</form>
</body>
</html>
