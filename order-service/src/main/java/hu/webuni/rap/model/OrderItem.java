package hu.webuni.rap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem{

	@Id
	@GeneratedValue
	private Long id;
	private Long product_id;
	private String name;
	private double price;
	private int piece;

	@ManyToOne//(fetch= FetchType.EAGER)
	@JoinColumn(name="order_id", nullable=false)
	private WebshopOrder webshop_order;
}
