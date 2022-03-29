package hu.webuni.rap.mapper;

import org.mapstruct.Mapper;

import hu.webuni.rap.api.model.ProductHistoryDto;
import hu.webuni.rap.model.HistoryData;
import hu.webuni.rap.model.Product;

@Mapper(componentModel = "spring")
public interface HistoryDataMapper {

	ProductHistoryDto productHistoryDataToDto(HistoryData<Product> hd);

}
