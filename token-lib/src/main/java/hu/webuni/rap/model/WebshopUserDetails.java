package hu.webuni.rap.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class WebshopUserDetails extends User{
	private String email;
	private String facebookId;

	public WebshopUserDetails(String username
			, String password
			, Collection<? extends GrantedAuthority> authorities
			, String email
			, String facebookId) {
		super(username, password, authorities);
		this.email = email;
		this.facebookId = facebookId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

}
