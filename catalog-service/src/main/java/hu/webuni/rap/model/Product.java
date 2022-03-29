package hu.webuni.rap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Product.category", attributeNodes = @NamedAttributeNode(value = "category"))
@Audited
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private double price;
	
	@ManyToOne
	private Category category;
}
