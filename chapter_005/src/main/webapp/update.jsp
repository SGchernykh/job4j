<%@ page import="ru.job4j.servlets.Users" %>
<%@ page import="ru.job4j.servlets.MemoryStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add new user to database</h2>
<%Users user = MemoryStore.getInstance().findById(Integer.parseInt(request.getParameter("id")));%>
<form action = '<%=request.getContextPath()%>/users' method='post'>
    Name: <input type='text' name='name' value="<%=user.getName()%>"/><br />
    Login: <input type='text' name='login' value="<%=user.getLogin()%>"/><br />
    Email: <input type='text' name='email' value="<%=user.getEmail()%>"/><br />
    <input type="hidden" name="id" value="<%=Integer.parseInt(request.getParameter("id"))%>"/>
    <input type='submit' value='update' name='action'/>
</form>
</body>
</html>
