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
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>CreateData</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
        <th>
            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="update"/>
            </form>
        </th>
        <th>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" name="action" value="delete"/>
            </form>
        </th>
    </tr>
</c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/add" method="get">
    <input type="submit" value="add"/>
</form>
</body>
</html>
