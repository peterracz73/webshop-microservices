package hu.webuni.rap.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebshopUser {

	@Id
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	private String email;
	private String facebookId;
	
}
