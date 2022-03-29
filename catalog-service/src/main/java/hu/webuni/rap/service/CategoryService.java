package hu.webuni.rap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.rap.model.Category;
import hu.webuni.rap.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;

	public Category saveCategory(Category category) {
		Optional<Category> findByName = repository.findByName(category.getName());
		if (findByName.isEmpty()) {
			Category savedCategory = repository.save(category);
			return savedCategory;
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public List<Category> getAllCategories() {
		return repository.findAll();
	}
	
	
}
