<%@ page import="ru.job4j.servlets.Users" %>
<%@ page import="ru.job4j.servlets.MemoryStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new user to database</h2>
<form action = '<%=request.getContextPath()%>/users' method = 'post'>
    Name: <input type = 'text' name = 'name'/><br />
    Login: <input type = 'text' name = 'login'/><br />
    Email: <input type = 'text' name = 'email'/><br />
    <input type = 'submit' value = 'add' name='action'/>
</form>
</body>
</html>
