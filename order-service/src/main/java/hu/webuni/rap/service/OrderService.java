package hu.webuni.rap.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.rap.model.OrderItem;
import hu.webuni.rap.model.WebshopOrder;
import hu.webuni.rap.repository.ItemRepository;
import hu.webuni.rap.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository repoOrder;
	private final ItemRepository repoItem;
	
	@Transactional
	public WebshopOrder saveOrder(WebshopOrder order) {
		order = repoOrder.save(order);
		for (OrderItem item : order.getItems()) {
			item.setWebshop_order(order);
			item = repoItem.save(item);
		}
		return order;
	}

	public List<WebshopOrder> getAllOrders(String username) {
		if (username==null)
			return repoOrder.findAllWithItems();
		else
			return repoOrder.findByUsernameWithItems(username);
	}

	public List<WebshopOrder> getAllOrdersWithoutItems(String username) {
		if (username==null)
			return repoOrder.findAll();
		else
			return repoOrder.findByUsername(username);
			
	}
	
}
