<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new user to database</h2>
<form action = '${pageContext.servletContext.contextPath}/users' method = 'post'>
    Name: <input type = 'text' name = 'name'/><br />
    Login: <input type = 'text' name = 'login'/><br />
    Email: <input type = 'text' name = 'email'/><br />
    <input type = 'submit' value = 'add' name='action'/>
</form>
</body>
</html>
