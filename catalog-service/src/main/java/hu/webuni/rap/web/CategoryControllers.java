package hu.webuni.rap.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import hu.webuni.rap.api.CategoryControllerApi;
import hu.webuni.rap.api.model.CategoryDto;
import hu.webuni.rap.mapper.CategoryMapper;
import hu.webuni.rap.model.Category;
import hu.webuni.rap.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CategoryControllers implements CategoryControllerApi{

	private final NativeWebRequest nativeWebRequest;
	private final CategoryMapper mapper;
	private final CategoryService service;

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.of(nativeWebRequest);
	}

	@Override
	public ResponseEntity<CategoryDto> createNewCatalog(@Valid CategoryDto categoryDto) {
		Category savedCategory = service.saveCategory(mapper.dtoToCategory(categoryDto));
		return ResponseEntity.ok(mapper.categoryToDto(savedCategory));
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<Category> categories = service.getAllCategories();
		List<CategoryDto> categoryDtos = mapper.categoriesToDtos(categories);
		return ResponseEntity.ok(categoryDtos);
	}


}
