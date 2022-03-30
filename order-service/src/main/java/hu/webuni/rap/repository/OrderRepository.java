package hu.webuni.rap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.rap.model.WebshopOrder;

public interface OrderRepository extends JpaRepository<WebshopOrder, Long>{

	@EntityGraph(attributePaths = {"items"})
	@Query("select a from WebshopOrder a")
	List<WebshopOrder> findAllWithItems();

	List<WebshopOrder> findByUsername(String username);

	@EntityGraph(attributePaths = {"items"})
	@Query("select a from WebshopOrder a where a.username = :username")
	List<WebshopOrder> findByUsernameWithItems(String username);

}
