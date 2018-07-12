package ru.job4j.repository;

/**
 * SaleOrder CRUD Repository.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.SaleOrder;

@Repository
public interface SaleOrderRepository extends CrudRepository<SaleOrder, Integer> {

}