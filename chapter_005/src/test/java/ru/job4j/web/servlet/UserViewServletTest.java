package ru.job4j.web.servlet;
/**
 * UserViewServletTest.
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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class UserViewServletTest {
    /*
    @Test
    public void userUpdateUser() throws IOException {
        final UserViewServlet updateUser = new UserViewServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("4");
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("name")).thenReturn("user123");
        when(request.getParameter("login")).thenReturn("user1");
        when(request.getParameter("password")).thenReturn("user1");
        when(request.getParameter("email")).thenReturn("user1@user1.ru");
        updateUser.doPost(request, response);
        verify(request, atLeast(1)).getParameter("action");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("id");
        final List<Users> users = DBStore.getInstance().findAll();
        Users user = null;
        for (Users u : users) {
            if ("user1".equalsIgnoreCase(u.getLogin()) && "user1".equalsIgnoreCase(u.getPassword())) {
                user = u;
            }
        }
        assertThat(user.getName(), is("user123"));
    }
    */
}