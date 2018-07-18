package ru.job4j.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.domain.SaleOrder;
import ru.job4j.service.SaleOrderService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SaleOrderControllerTest {

    @MockBean
    SaleOrderService saleOrderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenGetMappingRootThenReturnIndexPage() throws Exception {
        this.mockMvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenGetMappingListThenReturnListOfAdverts() throws Exception {
        this.mockMvc.perform(
                get("/list"))
                .andExpect(status().isOk());
        verify(this.saleOrderService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenPostMappingAddThenReturnAddAdvertPage() throws Exception {
        this.mockMvc.perform(
                get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addOrder"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"user"})
    public void whenPostMappingUpdateThenRedirectRoot() throws Exception {
        final SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(1);
        saleOrder.setSale(false);
        Mockito.when(this.saleOrderService.getById(1)).thenReturn(saleOrder);
        Mockito.when(this.saleOrderService.save(saleOrder)).thenReturn(saleOrder);
        this.mockMvc.perform(
                post("/update").param("id", "1"))
                .andExpect(status().is3xxRedirection());
    }
}