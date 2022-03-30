package hu.webuni.rap.web;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.OPTIONS;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.rap.dto.WebshopOrderDto;
import hu.webuni.rap.mapper.OrderMapper;
import hu.webuni.rap.model.WebshopOrder;
import hu.webuni.rap.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderRestcontroller {

	private final OrderService service;
	private final OrderMapper mapper;
	
	@PostMapping("/orders")
	public WebshopOrderDto postNewOrder(@RequestBody WebshopOrderDto order) {
		order.setStatus("PENDING");
		return mapper.webshopOrderToDto(service.saveOrder(mapper.dtoToWebshopOrder(order)));
	}

	@GetMapping("/orders")
	public List<WebshopOrderDto> getAllOrders(
			@RequestParam(value = "full", required = false, defaultValue = "false") Boolean full,
			@RequestParam(value = "username", required = false) String username) {
		if (full) {
			List<WebshopOrder> order = service.getAllOrders(username);
			return mapper.webshopOrdersToDtos(order);
		}else {
			List<WebshopOrder> order = service.getAllOrdersWithoutItems(username);
			return mapper.webshopOrdersToDtosWithoutItems(order);
		}
	}
}
