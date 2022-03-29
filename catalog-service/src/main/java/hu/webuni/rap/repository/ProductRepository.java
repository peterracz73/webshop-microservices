package hu.webuni.rap.repository;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import hu.webuni.rap.model.Product;
import hu.webuni.rap.model.QProduct;

public interface ProductRepository extends 
					 JpaRepository<Product, Long>
					,QuerydslPredicateExecutor<Product>
					,QuerydslBinderCustomizer<QProduct>
					,QuerydslWithEntityGraphRepository<Product>
{


	
	@Override
	default void customize(QuerydslBindings bindings, QProduct product) {
		bindings.bind(product.name).first((path, value) -> path.containsIgnoreCase(value));
		bindings.bind(product.category.name).first((path, value) -> path.containsIgnoreCase(value));
		
		bindings.bind(product.price).all((path, values) -> {
			if (values.size() ==1) {
				Iterator<? extends Double> iterator = values.iterator();
				Double from = iterator.next();
				return Optional.of(path.goe(from));
			}
			if (values.size() >1) {
				
				Iterator<? extends Double> iterator = values.iterator();
				Double from = iterator.next();
				Double to	 = iterator.next();
				return Optional.of(path.between(from, to));
			}
			return Optional.empty();
		});
		
	}

	public Optional<Product> findByName(String name);

}
