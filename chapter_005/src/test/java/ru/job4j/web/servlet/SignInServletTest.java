package ru.job4j.web.servlet;
/**
 * SignInServletTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SignInServletTest {
    /*
    @Test
    public void returnError() throws ServletException, IOException {
        final SignInServlet signIn = new SignInServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getAttribute("error")).thenReturn("Credential invalid!");
        when(request.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);
        signIn.doPost(request, response);
        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getRequestDispatcher("/WEB-INF/views/signin.jsp");
        assertThat(request.getAttribute("error"), is("Credential invalid!"));
    }
    */
}