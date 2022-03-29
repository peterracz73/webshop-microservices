package hu.webuni.rap.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.rap.model.Category;
import hu.webuni.rap.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitDbService {

	private final CategoryRepository categoryRepository;
	
	public void checkDatasAndCreateIfNeed() {
		checkCategory();
	}
	
	private void checkCategory() {
		Optional<Category> findByName = categoryRepository.findByName("computer");
		if (findByName.isEmpty()) {
			categoryRepository.save(new Category(0, "computer"));
		}
		Optional<Category> findByName2 = categoryRepository.findByName("mobil");
		if (findByName2.isEmpty()) {
			categoryRepository.save(new Category(0, "mobil"));
		}
	}
}
