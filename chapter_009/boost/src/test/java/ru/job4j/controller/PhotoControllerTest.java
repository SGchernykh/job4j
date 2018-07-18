package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.Photo;
import ru.job4j.service.PhotoService;


import java.io.File;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhotoService service;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsImageAndIdParameterThenImageServiceCallMethodGetById() throws Exception {
        File tempFile = File.createTempFile("test", "jpg");
        Photo photo = new Photo();
        photo.setImageURL(tempFile.getAbsolutePath());
        Mockito.when(service.getById(1)).thenReturn(photo);
        this.mockMvc.perform(get("/image/1"))
                .andExpect(status().isOk());
        verify(this.service, times(1)).getById(1);
    }
}