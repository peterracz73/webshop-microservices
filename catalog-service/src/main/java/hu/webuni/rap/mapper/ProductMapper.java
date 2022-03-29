package hu.webuni.rap.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.rap.api.model.ProductDto;
import hu.webuni.rap.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	public ProductDto productToDto(Product product);
	
	public Product dtoToProduct(ProductDto productDto);
	
	public List<ProductDto> productsToDtod(List<Product> products);
	
	public List<Product> dtosToProducts(List<ProductDto> productDtos);
}
