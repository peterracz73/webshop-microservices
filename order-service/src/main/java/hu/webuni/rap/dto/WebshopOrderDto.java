package hu.webuni.rap.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebshopOrderDto {
	
	private Long id;
	private String username;
	private String shippingAddress;
	private LocalDateTime orderDate;

	private Set<OrderItemDto> items;
	private String status;
}
