package hu.webuni.rap.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import hu.webuni.rap.api.ProductControllerApi;
import hu.webuni.rap.api.model.ProductDto;
import hu.webuni.rap.mapper.ProductMapper;
import hu.webuni.rap.model.Product;
import hu.webuni.rap.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {
	private final NativeWebRequest nativeWebRequest;
	private final ProductMapper mapper;
	private final ProductService service;

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.of(nativeWebRequest);
	}

	@Override
	public ResponseEntity<List<ProductDto>> getAllPoducts() {
		List<Product> products = service.getAllProducts();
		return ResponseEntity.ok(mapper.productsToDtod(products));
	}

	@Override
	public ResponseEntity<ProductDto> postNewProduct(@Valid ProductDto productDto) {
		Product savedProduct = service.saveProduct(mapper.dtoToProduct(productDto));
		return ResponseEntity.ok(mapper.productToDto(savedProduct));
	}

	@Override
	public ResponseEntity<Void> deleteProduct(@Valid Long id) {
		service.deleteProdutcById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<ProductDto> modifyProduct(@Valid ProductDto productDto) {
		Product savedProduct = service.saveProduct(mapper.dtoToProduct(productDto));
		return ResponseEntity.ok(mapper.productToDto(savedProduct));
	}

}
