package hu.webuni.rap.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
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

	@CacheEvict (value="PriceOfProductHistory", key = "#product.id")
	public Product saveProduct(Product product) {
		return repository.save(product);
	}

	@CacheEvict (value="PriceOfProductHistory", key = "#id")
	public void deleteProdutcById(@Valid Long id) {
		try {
			repository.deleteById(id);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
