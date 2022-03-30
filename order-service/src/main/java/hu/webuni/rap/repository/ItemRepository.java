package hu.webuni.rap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.rap.model.OrderItem;

public interface ItemRepository extends JpaRepository<OrderItem, Long>{

}
