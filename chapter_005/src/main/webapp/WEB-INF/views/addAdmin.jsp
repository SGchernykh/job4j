<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new user to database</h2>
<form action="${pageContext.servletContext.contextPath}/admin" method="post">
    <label>Name</label><br/>
    <input type = 'text' name = 'name'/><br/>
    <label>Login</label><br/>
    <input type="text" name="login"><br/>
    <label>Password</label><br/>
    <input type="text" name="password"><br/>
    <label>Email</label><br/>
    <input type="text" name="email"><br/>
    <label>Role</label><select name="role_id">
        <c:forEach items="${roles}" var="role">
            <option value="${role.id}" selected>${role.role}</option>
        </c:forEach>
    </select><br/>
    <br/>
    <input type="hidden" name="role" value="${role.role}"/>
    <input type="submit" name="action" value="add"/>
</form>
</body>
</html>
