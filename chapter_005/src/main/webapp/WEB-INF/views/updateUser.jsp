<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update new user to database</h2>
<form action="${pageContext.servletContext.contextPath}/user" method="post">
    <c:set var="user" value="${user}"></c:set>
    <input type='hidden' name="id" value="${user.id}">.
    <label>Name</label><br/>
    <input type="text" name="name" value="${user.name}"><br/>
    <label>Login</label><br/>
    <input type="text" name="login" value="${user.login}"><br/>
    <label>Password</label><br/>
    <input type="password" name="password" value="${user.password}"><br/>
    <label>Email</label><br/>
    <input type="text" name="email" value="${user.email}"><br/>
    <br/>
    <input type="submit" name="action" value="update"/>
</form>
</body>
</html>
