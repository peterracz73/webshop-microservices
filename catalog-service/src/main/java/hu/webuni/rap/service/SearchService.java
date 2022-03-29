package hu.webuni.rap.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import hu.webuni.rap.model.Product;
import hu.webuni.rap.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {
	
	private final ProductRepository repository;

	public List<Product> search(Predicate predicate, Pageable pageable) {
		List<Product> products = repository.findAll(predicate, pageable).getContent();
		return products;
	}

}
