<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update new user to database</h2>
<c:set var="user" value="${user}"></c:set>
<form action = '${pageContext.servletContext.contextPath}/' method='post'>

    <label>Name </label><input type="text" name="name" value="${user.name}"><br/>
    <label>Login </label><input type="text" name="login" value="${user.login}"><br/>
    <label>Email </label><input type="text" name="email" value="${user.email}"><br/>
    <input type="hidden" name="id" value="${user.id}"/>
    <input type='submit' value='update' name='action'/>
</form>
</body>
</html>
