package ru.job4j.service;

/**
 * SaleOrderService.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.models.*;
import ru.job4j.models.components.*;
import ru.job4j.repository.*;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private SecurityService securityService;

    /**
     * Save Car in storage.
     * @param value Car.
     * @return Car.
     */
    public SaleOrder save(final SaleOrder value) {
        return this.saleOrderRepository.save(value);
    }

    /**
     * Get SaleOrder by id from storage.
     * @param id Id.
     * @return SaleOrder.
     */
    public SaleOrder getById(final int id) {
        return this.saleOrderRepository.findById(id).get();
    }

    /**
     * Get All SaleOrder from storage.
     * @return List SaleOrder.
     */
    public List<SaleOrder> getAll() {
        return (List<SaleOrder>) this.saleOrderRepository.findAll();
    }

    /**
     * Prepare SaleOrder.
     * @param model Model For Filling Order.
     * @param file Photo file.
     * @return SaleOrder.
     */
    public SaleOrder prepareSaleOrder(final ModelForFillingOrder model, final MultipartFile file) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setAuthor(this.getUser());
        saleOrder.setTitle(model.getTitle());
        saleOrder.setSale(false);
        saleOrder.setDescription(model.getDescription());
        saleOrder.setPrice(Integer.parseInt(model.getPrice()));
        saleOrder.setCreated(new Timestamp(System.currentTimeMillis()));
        saleOrder.setPhoto(this.photoService.save(new WritePhotoToDisk().writePhotoToDisk(file)));
        saleOrder.setCar(this.carService.save(this.fillCar(model)));
        saleOrder.setCity(new City(Integer.parseInt(model.getCity())));
        return saleOrder;
    }

    /**
     * Create test user.
     * @ User
     */
    private User getUser() {
        User user = this.userService.getByLogin(this.securityService.findLoggedUser());
        return user;
    }

    /**
     * Fill Car.
     * @param model Model For Filling Order.
     * @return Car.
     */
    private Car fillCar(final ModelForFillingOrder model) {
        Car car = new Car();
        car.setBrand(new Brand(Integer.parseInt(model.getBrand())));
        car.setModel(new Model(Integer.parseInt(model.getModel())));
        car.setCarBody(new CarBody(Integer.parseInt(model.getCarBody())));
        car.setEngine(new Engine(Integer.parseInt(model.getEngine())));
        car.setTransmission(new Transmission(Integer.parseInt(model.getTransmission())));
        car.setDriveUnit(new DriveUnit(Integer.parseInt(model.getDriveUnit())));
        return car;
    }
}