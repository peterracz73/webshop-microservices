package hu.webuni.rap.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedEntityGraph(name = "WebshopOrder.items", attributeNodes = @NamedAttributeNode(value = "items"))
public class WebshopOrder {
	
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String shippingAddress;
	private LocalDateTime orderDate;
	
	@OneToMany(mappedBy = "webshop_order", orphanRemoval = true)
	private Set<OrderItem> items;
	private String status;
}
