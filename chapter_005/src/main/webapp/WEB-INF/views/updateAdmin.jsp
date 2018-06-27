<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update new user to database</h2>
<form action="${pageContext.servletContext.contextPath}/admin" method="post">
<c:set var="user" value="${user}"></c:set>
<input type='hidden' name="id" value="${user.id}">
<label>Name</label><br/>
<input type = 'text' name = 'name' value="${user.name}"/><br/>
<label>Login</label><br/>
<input type="text" name="login" value="${user.login}"><br/>
<label>Password</label><br/>
<input type="password" name="password" value="${user.password}"><br/>
<label>Email</label><br/>
<input type="text" name="email" value="${user.email}"><br/>
<label>Role</label><br/>
<select name="role_id">
    <c:forEach items="${roles}" var="role">
        <option value="${role.id}" selected>${role.role}</option>
    </c:forEach>
</select>
<br/>
    <input type="hidden" name="role" value="${role.role}"/>
    <input type='submit' value='update' name='action'/>
</form>
</body>
</html>
