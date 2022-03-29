package hu.webuni.rap.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.rap.model.Product;
import hu.webuni.rap.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	public void deleteProdutcById(@Valid Long id) {
		try {
			repository.deleteById(id);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
