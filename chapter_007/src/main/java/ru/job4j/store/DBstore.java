package ru.job4j.store;

/**
 * DataBaseStore.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
//import org.hibernate.query.Query;
import javax.persistence.Query;

import ru.job4j.models.*;
import ru.job4j.models.components.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Function;

public class DBstore implements Store {
    private static final Store INSTANCE = new DBstore();
    // private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("JpaCarStore");

    /**
     * Constructor.
     */
    private DBstore() {
    }

    /**
     * Dispatch pattern.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
       return this.tx(session -> {
            User user = null;
            Query query = session.createQuery("FROM User WHERE login =:log AND password =:pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
//            List<User> userList = query.list();
            List<User> userList = query.getResultList();
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            return user;
        });
    }

    @Override
    public boolean addUser(User user) {
       return this.tx(session -> {
//          session.save(user);
           session.persist(user);
          return true;
       });
    }


    @Override
    public boolean addSaleOrder(Photo photo, Car car, SaleOrder saleOrder) {
        return this.tx(session -> {
            if (photo != null) {
                session.persist(photo);
            }
            session.persist(car);
            session.persist(saleOrder);
            return true;
        });
    }

    @Override
    public List<SaleOrder> getAllOrder() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM SaleOrder");
//            List<SaleOrder> orders = query.list();
            List<SaleOrder> orders = query.getResultList();
            return orders;
        });
    }

    @Override
    public List<Engine> getAllEngine() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM Engine");
//            List<Engine> engine = query.list();
            List<Engine> engine = query.getResultList();
            return engine;
        });
    }

    @Override
    public List<Transmission> getAllTransmission() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM Transmission");
//            List<Transmission> transmission = query.list();
            List<Transmission> transmission = query.getResultList();
            return transmission;
        });
    }

    @Override
    public List<City> getAllCity() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM City");
//            List<City> city = query.list();
            List<City> city = query.getResultList();
            return city;
        });
    }

    @Override
    public List<Brand> getAllBrand() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM Brand");
//            List<Brand> brand = query.list();
            List<Brand> brand = query.getResultList();
            return brand;
        });
    }

    @Override
    public Brand findBrandByName(String name) {
        return this.tx(session -> {
            Brand brand = null;
            Query query = session.createQuery("FROM Brand WHERE name=:name");
            query.setParameter("name", name);
            /*if (!query.list().isEmpty()) {
                brand = (Brand) query.list().get(0);
            }*/
            if (!query.getResultList().isEmpty()) {
                brand = (Brand) query.getResultList().get(0);
            }
            return brand;
        });
    }

    @Override
    public List<Model> getModelWithTheBrand(int brandId) {
        return this.tx(session -> {
//            Query query = session.createQuery("FROM Model WHERE brand_id=:id");
            Query query = session.createQuery("FROM Model WHERE brand_id=:id");
            query.setParameter("id", brandId);
//            List<Model> model = query.list();
            List<Model> model = query.getResultList();
            return model;
        });
    }

    @Override
    public List<CarBody> getAllCarBody() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM CarBody");
//            List<CarBody> carBody = query.list();
            List<CarBody> carBody = query.getResultList();
            return carBody;
        });
    }

    @Override
    public List<DriveUnit> getAllDriveUnit() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM DriveUnit");
//            List<DriveUnit> driveUnit = query.list();
            List<DriveUnit> driveUnit = query.getResultList();
            return driveUnit;
        });
    }

    @Override
    public CarBody findCarBodyById(int idCarBody) {
        return this.tx(session -> {
            CarBody carBody = null;
            Query query = session.createQuery("FROM CarBody WHERE id=:id");
            query.setParameter("id", idCarBody);
            if (!query.getResultList().isEmpty()) {
                carBody = (CarBody) query.getResultList().get(0);
            }
            return carBody;
        });
    }

    @Override
    public DriveUnit findDriveUnitById(int idDriveUnit) {
        return this.tx(session -> {
            DriveUnit driveUnit = null;
            Query query = session.createQuery("FROM DriveUnit WHERE id=:id");
            query.setParameter("id", idDriveUnit);
            if (!query.getResultList().isEmpty()) {
                driveUnit = (DriveUnit) query.getResultList().get(0);
            }
            return driveUnit;
        });
    }

    @Override
    public Model findModelById(int idModel) {
        return this.tx(session -> {
            Model model = null;
            Query query = session.createQuery("FROM Model WHERE id=:id");
            query.setParameter("id", idModel);
            if (!query.getResultList().isEmpty()) {
                model = (Model) query.getResultList().get(0);
            }
            return model;
        });
    }

    @Override
    public Transmission findTransmissionById(int idTransmission) {
        return this.tx(session -> {
            Transmission transmission = null;
            Query query = session.createQuery("FROM Transmission WHERE id=:id");
            query.setParameter("id", idTransmission);
            if (!query.getResultList().isEmpty()) {
                transmission = (Transmission) query.getResultList().get(0);
            }
            return transmission;
        });
    }

    @Override
    public Engine findEngineById(int idEngine) {
        return this.tx(session -> {
            Engine engine = null;
            Query query = session.createQuery("FROM Engine WHERE id=:id");
            query.setParameter("id", idEngine);
            if (!query.getResultList().isEmpty()) {
                engine = (Engine) query.getResultList().get(0);
            }
            return engine;
        });
    }

    @Override
    public City findCityById(int idCity) {
        return this.tx(session -> {
            City brand = null;
            Query query = session.createQuery("FROM City WHERE id=:id");
            query.setParameter("id", idCity);
            if (!query.getResultList().isEmpty()) {
                brand = (City) query.getResultList().get(0);
            }
            return brand;
        });
    }

    @Override
    public SaleOrder getOrderById(int id) {
        return this.tx(session -> {
            SaleOrder saleOrder = null;
            Query query = session.createQuery("FROM SaleOrder WHERE id=:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                saleOrder = (SaleOrder) query.getResultList().get(0);
            }
            return saleOrder;
        });
    }

    @Override
    public boolean updateSaleStatus(SaleOrder saleOrder) {
        return this.tx(session -> {
            session.merge(saleOrder);
            return true;
        });
    }

    @Override
    public Photo getPhotoById(int id) {
        return this.tx(session -> {
            Photo photo = null;
            Query query = session.createQuery("FROM Photo WHERE id=:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                photo = (Photo) query.getResultList().get(0);
            }
            return photo;
        });
    }

    /**
     * Pattern wrapper.
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<EntityManager, T> command) {
        final EntityManager session = this.factory.createEntityManager();
//        final Session session = factory.openSession();
//        final Transaction tx = session.beginTransaction();
        session.getTransaction().begin();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        } finally {
//            tx.commit();
            session.getTransaction().commit();
            session.close();
        }
    }
}