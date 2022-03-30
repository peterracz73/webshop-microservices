package hu.webuni.rap.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.rap.dto.WebshopOrderDto;
import hu.webuni.rap.model.WebshopOrder;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	public WebshopOrder dtoToWebshopOrder(WebshopOrderDto orderDto );
	
	public WebshopOrderDto webshopOrderToDto(WebshopOrder order);

	public List<WebshopOrderDto> webshopOrdersToDtos(List<WebshopOrder> orders);
	
	@Named("WithoutItems")
	@Mapping(ignore = true, target = "items")
	public WebshopOrderDto webshopOrderToDtosWithoutItem(WebshopOrder order);

	@IterableMapping(qualifiedByName = "WithoutItems")
	public List<WebshopOrderDto> webshopOrdersToDtosWithoutItems(List<WebshopOrder> orders);

}
