package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.SaleOrder;

@Repository
public interface SaleOrderRepository extends CrudRepository<SaleOrder, Integer> {

}
