package ru.job4j.web.servlet;
/**
 * AdminViewServletTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import ru.job4j.web.model.Users;
import ru.job4j.web.store.DBStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdminViewServletTest {
    @Test
    public void adminAddUser() throws IOException {
        final AdminViewServlet addUser = new AdminViewServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("name")).thenReturn("user1");
        when(request.getParameter("login")).thenReturn("user1");
        when(request.getParameter("password")).thenReturn("user1");
        when(request.getParameter("email")).thenReturn("user1@user1.ru");
        when(request.getParameter("role_id")).thenReturn("2");
        when(request.getParameter("role")).thenReturn("user");
        addUser.doPost(request, response);
        verify(request, atLeast(1)).getParameter("action");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("role_id");
        verify(request, atLeast(1)).getParameter("role");
        final List<Users> users = DBStore.getInstance().findAll();
        Users user = null;
        for (Users u : users) {
            if ("user1".equalsIgnoreCase(u.getLogin()) && "user1".equalsIgnoreCase(u.getPassword())) {
                user = u;
            }
        }
        //assertThat(user.getLogin(), is("user1"));
        //assertThat(user.getPassword(), is("user1"));
        //assertThat(user.getEmail(), is("user1@user1.ru"));
    }

}