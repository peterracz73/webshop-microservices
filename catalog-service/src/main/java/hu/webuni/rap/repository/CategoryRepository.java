package hu.webuni.rap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.rap.model.Category;

public interface CategoryRepository extends 
										JpaRepository<Category, Integer>{

	public Optional<Category> findByName(String name);
}
