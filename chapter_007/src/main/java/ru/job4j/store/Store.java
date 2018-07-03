package ru.job4j.store;

/**
 * Store.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import ru.job4j.models.*;
import ru.job4j.models.components.*;

import java.util.List;

public interface Store {

    /**
     * Get user with login and password.
     * @param login Login.
     * @param password Password.
     * @return User.
     */
    User getUserByLoginAndPassword(String login, String password);

    /**
     * Add User in User Table
     * @param user User.
     * @return true.
     */
    boolean addUser(User user);

    /**
     * Get all Order.
     * @return List Order.
     */
    List<SaleOrder> getAllOrder();

    /**
     * Get All Engine.
     * @return List Engine.
     */
    List<Engine> getAllEngine();

    /**
     * Get All Transmission.
     * @return List Transmission.
     */
    List<Transmission> getAllTransmission();

    /**
     * Get All City.
     * @return List City.
     */
    List<City> getAllCity();

    /**
     * Get All Brand.
     * @return List Brand.
     */
    List<Brand> getAllBrand();

    /**
     * Get Model with the brand.
     * @param brandId id brand.
     * @return List model.
     */
    List<Model> getModelWithTheBrand(int brandId);

    /**
     * Get All Car Body
     * @return List Car Body
     */
    List<CarBody> getAllCarBody();

    /**
     * Get All Drive Unit.
     * @return List Drive Unit.
     */
    List<DriveUnit> getAllDriveUnit();
    /**
     * Find Brand by Name.
     * @param name Name.
     * @return Brand.
     */
    Brand findBrandByName(String name);

    /**
     * Find Car Body by id.
     * @param idCarBody Id.
     * @return Car Body.
     */
    CarBody findCarBodyById(int idCarBody);

    /**
     * Find Drive Unit by id.
     * @param idDriveUnit id.
     * @return Drive Unit
     */
    DriveUnit findDriveUnitById(int idDriveUnit);

    /**
     * Find Model by id.
     * @param idModel id.
     * @return Model.
     */
    Model findModelById(int idModel);

    /**
     * Find Transmission by id.
     * @param idTransmission id.
     * @return Transmission
     */
    Transmission findTransmissionById(int idTransmission);

    /**
     * Get Sale Order by id.
     * @param id id.
     * @return Sale Order.
     */
    SaleOrder getOrderById(int id);

    /**
     * Get Photo by id.
     * @param id id.
     * @return Photo.
     */
    Photo getPhotoById(int id);

    /**
     * Find Engine by id.
     * @param idEngine id.
     * @return Engine.
     */
    Engine findEngineById(int idEngine);

    /**
     * Find City by id.
     * @param idCity id.
     * @return City.
     */
    City findCityById(int idCity);

    /**
     * Add sale Order.
     * @param photo Photo.
     * @param car Car.
     * @param saleOrder Sale Order.
     * @return true.
     */
    boolean addSaleOrder(Photo photo, Car car, SaleOrder saleOrder);

    /**
     * Update sale status in Sale Order.
     * @param saleOrder Sale Order.
     * @return true.
     */
    boolean updateSaleStatus(SaleOrder saleOrder);
}