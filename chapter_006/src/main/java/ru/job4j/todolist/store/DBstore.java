package ru.job4j.todolist.store;
/**
 * Item.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.todolist.model.Item;

import java.util.List;
import java.util.function.Function;

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
        this.tx(session -> session.save(newItem));
    }

    @Override
    public List<Item> getAll() {
        return this.tx(session -> session.createQuery("from Item").list());
    }

    @Override
    public boolean update(Item newItem) {
        return this.tx(
                session -> {
                    session.update(newItem);
                    return true;
                }
        );
    }

    @Override
    public Item findById(int id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Item where id =:idNew");
                    query.setParameter("idNew", id);
                    return (Item) query.list().get(0);
                }
        );
    }

    @Override
    protected void finalize() throws Throwable {
        this.factory.close();
    }

    /**
     * Pattern wrapper.
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }
}
