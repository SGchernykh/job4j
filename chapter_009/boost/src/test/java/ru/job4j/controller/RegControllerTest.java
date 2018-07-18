package ru.job4j.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.domain.User;
import ru.job4j.service.UserService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenGetMappingEqualsRegisterThenReturnRegisterView() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenPostMappingEqualsRegisterThenReturnIndexView() throws Exception {
        Mockito.when(userService.getByLogin("user")).thenReturn(null);
        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenPostMappingEqualsRegisterWithErrorShouldReturnRegisterView() throws Exception {
        User u = new User();
        u.setName("test");
        Mockito.when(userService.getByLogin(u.getLogin())).thenReturn(u);

        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

}