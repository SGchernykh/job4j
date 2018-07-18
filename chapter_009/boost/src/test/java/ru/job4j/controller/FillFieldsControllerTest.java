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
import ru.job4j.domain.components.Brand;
import ru.job4j.service.*;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(FillFieldsController.class)
public class FillFieldsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;
    @MockBean
    private CarBodyService carBodyService;
    @MockBean
    private ModelService modelService;
    @MockBean
    private DriveUnitService driveUnitService;
    @MockBean
    private EngineService engineService;
    @MockBean
    private TransmissionService transmissionService;
    @MockBean
    private CityService cityService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarBrandThenCallCarBrandServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/carBrand"))
                .andExpect(status().isOk());

        verify(this.brandService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarModelThenCallCarBrandServiceMethodGetAll() throws Exception {
        final Brand brand = new Brand();
        brand.setId(10);
        brand.setName("ford");
        this.mockMvc.perform(
                get("/carModel").param("brand", "10"))
                .andExpect(status().isOk());
        verify(this.modelService, times(1)).getModelByBrandId(brand.getId());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsEngineThenCallEngineServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/engine"))
                .andExpect(status().isOk());
        verify(this.engineService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsTransmissionCallTransmissionServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/transmission"))
                .andExpect(status().isOk());
        verify(this.transmissionService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarBodyThenCallCarBodyServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/carBody"))
                .andExpect(status().isOk());
        verify(this.carBodyService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsDriveUnitThenCallDriveUnitServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/driveUnit"))
                .andExpect(status().isOk());
        verify(this.driveUnitService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCityThenCallCityServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/city"))
                .andExpect(status().isOk());
        verify(this.cityService, times(1)).getAll();
    }
}