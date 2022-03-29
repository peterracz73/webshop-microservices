package hu.webuni.rap.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.rap.api.model.CategoryDto;
import hu.webuni.rap.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	public Category dtoToCategory(CategoryDto categoryDto);
	
	public CategoryDto categoryToDto(Category category);

	public List<CategoryDto> categoriesToDtos(List<Category> categories); 
}
