package ru.job4j.todolist.store;
/**
 * Item.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.todolist.model.Item;

import java.util.List;

public class DBstore implements Store {
    private static final Store INSTANCE = new DBstore();
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

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
    public void createItem(Item newItem) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescription(newItem.getDescription());
        item.setDone(newItem.getDone());
        item.setCreated(newItem.getCreated());
        session.save(item);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<Item> getAll() {
        List<Item> items = null;
        Session session = this.factory.openSession();
        session.beginTransaction();
        items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    @Override
    public void update(Item newItem) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        session.update(newItem);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Item findById(int id) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where id =:idNew");
        query.setParameter("idNew", id);
        Item item = (Item) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    protected void finalize() throws Throwable {
        this.factory.close();
    }
}
