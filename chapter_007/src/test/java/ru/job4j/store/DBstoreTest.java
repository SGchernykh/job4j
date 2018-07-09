package ru.job4j.store;

/**
 * DBstoreTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DBstoreTest {
@Before
public void liquibase() {
    Liquibase liquibase;
    try {
        liquibase = new Liquibase("src/test/resource/liquibase/db.changelog-master.xml", new FileSystemResourceAccessor(), new HsqlConnection(DriverManager.getConnection("jdbc:hsqldb:mem:carstore;sql.enforce_size=false", "sa", "")));
        liquibase.update("src/test/resource/liquibase/db.changelog-0.1.0.xml");
    } catch (LiquibaseException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    @Test
    public void whenAddUserAndGetUserByLoginAndPassword() {
        final String name = "user1";
        final String login = "user1";
        final String password = "user1";
        final User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        DBstore.getInstance().addUser(user);
        assertThat(user.getLogin(), is(DBstore.getInstance().getUserByLoginAndPassword(login, password).getLogin()));
    }

    @Test
    public void whenAddSaleOrderAndWhenGetOrderById() {
        final String name = "user1";
        final String login = "user1";
        final String password = "user1";
        final User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        DBstore.getInstance().addUser(user);
        final Car car = new Car();
        car.setBrand(DBstore.getInstance().findBrandByName("KIA"));
        car.setModel(DBstore.getInstance().findModelById(1));
        car.setCarBody(DBstore.getInstance().findCarBodyById(1));
        car.setEngine(DBstore.getInstance().findEngineById(1));
        car.setTransmission(DBstore.getInstance().findTransmissionById(1));
        car.setDriveUnit(DBstore.getInstance().findDriveUnitById(1));
        Photo photo = null;
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setTitle("test");
        saleOrder.setDescription("desc");
        saleOrder.setSale(false);
        saleOrder.setAuthor(user);
        saleOrder.setCreated(new Timestamp(System.currentTimeMillis()));
        saleOrder.setCity(DBstore.getInstance().findCityById(1));
        saleOrder.setPrice(200);
        saleOrder.setPhoto(photo);
        saleOrder.setCar(car);
        DBstore.getInstance().addSaleOrder(photo, car, saleOrder);
        assertThat(DBstore.getInstance().getAllOrder().get(0).getId(), is(saleOrder.getId()));
        assertThat(DBstore.getInstance().getOrderById(saleOrder.getId()).getId(), is(saleOrder.getId()));
    }


    @Test
    public void whenFindBrandByName() {
    String brand = "KIA";
    assertThat(DBstore.getInstance().findBrandByName(brand).getName(), is(brand));
    }

    @Test
    public void whenUpdateSaleStatus() {
        final String name = "user1";
        final String login = "user1";
        final String password = "user1";
        final User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        DBstore.getInstance().addUser(user);
        final Car car = new Car();
        car.setBrand(DBstore.getInstance().findBrandByName("KIA"));
        car.setModel(DBstore.getInstance().findModelById(1));
        car.setCarBody(DBstore.getInstance().findCarBodyById(1));
        car.setEngine(DBstore.getInstance().findEngineById(1));
        car.setTransmission(DBstore.getInstance().findTransmissionById(1));
        car.setDriveUnit(DBstore.getInstance().findDriveUnitById(1));
        Photo photo = null;
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setTitle("test");
        saleOrder.setDescription("desc");
        saleOrder.setSale(false);
        saleOrder.setAuthor(user);
        saleOrder.setCreated(new Timestamp(System.currentTimeMillis()));
        saleOrder.setCity(DBstore.getInstance().findCityById(1));
        saleOrder.setPrice(200);
        saleOrder.setPhoto(photo);
        saleOrder.setCar(car);
        DBstore.getInstance().addSaleOrder(photo, car, saleOrder);
        saleOrder.setSale(true);
        DBstore.getInstance().updateSaleStatus(saleOrder);
        assertThat(DBstore.getInstance().getOrderById(saleOrder.getId()).isSale(), is(true));
    }
}