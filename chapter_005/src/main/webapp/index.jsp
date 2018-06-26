<%@ page import="ru.job4j.servlets.Users" %>
<%@ page import="ru.job4j.servlets.MemoryStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% for (Users user : MemoryStore.getInstance().findAll()) {%>
    <tr>
        <th><%=user.getId()%></th>
        <th><%=user.getName()%></th>
        <th><%=user.getLogin()%></th>
        <th><%=user.getEmail()%></th>
        <th><%=user.getCreateDate()%></th>
        <th>
            <form action="<%=request.getContextPath()%>/update" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="update"/>
            </form>
        </th>
        <th>
            <form action="<%=request.getContextPath()%>/users" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" name="action" value="delete"/>
            </form>
        </th>
    </tr>
    <%}%>
</table>
<form action="<%=request.getContextPath()%>/add" method="get">
    <input type="submit" value="add"/>
</form>
</body>
</html>
