package ru.todo.repository;

/**
 * TaskRepository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.cfg.Configuration;
import ru.todo.domain.Task;


import java.util.List;
import java.util.function.Function;


@Repository
public class TaskRepository implements ITaskRepository {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<Task> getAllTask() {
        return this.tx(session -> {
            Query query = session.createQuery("FROM Task");
            List<Task> taskList = query.list();
            return taskList;
        });
    }

    @Override
    public Task getById(int id) {
        return this.tx(session -> {
            Task task = null;
            Query query = session.createQuery("FROM Task WHERE id=:id");
            query.setParameter("id", id);
            if (!query.getResultList().isEmpty()) {
                task = (Task) query.getResultList().get(0);
            }
            return task;
        });
    }

    @Override
    public boolean add(Task task) {
        return this.tx(session -> {
            session.persist(task);
            return true;
        });

    }

    @Override
    public boolean edit(Task task) {
        return this.tx(session -> {
            session.merge(task);
            return true;
        });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

}