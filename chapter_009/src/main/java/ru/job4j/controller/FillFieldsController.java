package ru.job4j.controller;

/**
 * FillFieldsController.
 * Controller to fill the fields in the form creating Order.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.models.City;
import ru.job4j.models.components.*;
import ru.job4j.service.*;

import java.util.List;

@Controller
public class FillFieldsController {
    @Autowired
    private CarBodyService carBodyService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private DriveUnitService driveUnitService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private TransmissionService transmissionService;

    @Autowired
    private CityService cityService;


    @GetMapping("/carBrand")
    public @ResponseBody
    List<Brand> getBrands() {
        return this.brandService.getAll();
    }


    @GetMapping("/carModel")
    public @ResponseBody
    List<Model> getModels(@RequestParam String brand) {
        return this.modelService.getModelByBrandId(Integer.parseInt(brand));
    }

    @GetMapping("/engine")
    public @ResponseBody
    List<Engine> getEngines() {
        return this.engineService.getAll();
    }

    @GetMapping("/transmission")
    public @ResponseBody
    List<Transmission> getTransmissions() {
        return this.transmissionService.getAll();
    }


    @GetMapping("/carBody")
    public @ResponseBody
    List<CarBody> getBodies() {
        return this.carBodyService.getAll();
    }

    @GetMapping("/driveUnit")
    public @ResponseBody
    List<DriveUnit> getDriverUnits() {
        return this.driveUnitService.getAll();
    }

    @GetMapping("/city")
    public @ResponseBody
    List<City> getCities() {
        return this.cityService.getAll();
    }
}