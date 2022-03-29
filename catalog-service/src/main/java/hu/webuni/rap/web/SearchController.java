package hu.webuni.rap.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.querydsl.core.types.Predicate;

import hu.webuni.rap.api.SearchControllerApi;
import hu.webuni.rap.api.model.ProductDto;
import hu.webuni.rap.mapper.ProductMapper;
import hu.webuni.rap.model.Product;
import hu.webuni.rap.service.SearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchControllerApi {
	
	private final NativeWebRequest nativeWebRequest;
	private final ProductMapper mapper;
	private final SearchService service;
	private final PageableHandlerMethodArgumentResolver pageResolver;
	private final QuerydslPredicateArgumentResolver predicateResolver;

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.of(nativeWebRequest);
	}

	@Override
	public ResponseEntity<List<ProductDto>> searchProducts(@Valid List<Double> price, @Valid String name,
			@Valid String categoryName, @Valid Integer page, @Valid Integer size, @Valid String sort) {
		
		Pageable pageable = createPageable("configPageable");
		Predicate predicate = createPredicate("configurePredicate");
		List<Product> products = service.search(predicate, pageable);
		List<ProductDto> productDtos = mapper.productsToDtod(products);
		return ResponseEntity.ok(productDtos);
	}

	public void configPageable(@SortDefault("id") Pageable pageable) {}
	
	private Pageable createPageable(String pageableConfigurationName) {
		Method method;
		try 
		{
			method = this.getClass().getMethod(pageableConfigurationName, Pageable.class);
		} 
		catch (NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		MethodParameter methodParameter = new MethodParameter(method, 0);
		ModelAndViewContainer mavContainer = null;
		WebDataBinderFactory binderFactory = null;
		Pageable pageable = pageResolver.resolveArgument(methodParameter, mavContainer, nativeWebRequest, binderFactory);
		return pageable;
	}

	public void configurePredicate(@QuerydslPredicate(root = Product.class) Predicate predicate) {}

	private Predicate createPredicate(String configMethodName) {
		Method method;
			try {
				method = this.getClass().getMethod(configMethodName, Predicate.class);
				MethodParameter methodParameter = new MethodParameter(method, 0);
				ModelAndViewContainer mavContainer = null;
				WebDataBinderFactory binderFactory = null;
				Predicate predicate = (Predicate) predicateResolver.resolveArgument(methodParameter, mavContainer, nativeWebRequest, binderFactory);
				return predicate;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}	

}
