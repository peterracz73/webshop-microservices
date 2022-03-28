package hu.webuni.rap.dto;

public class LoginDto {
	
	private String username;
	private String password;
	private String email;
	private String fbToken;

	public LoginDto() {
		super();
	}

	public LoginDto(String username, String password, String email, String fbToken) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fbToken = fbToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFbToken() {
		return fbToken;
	}

	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}

}
