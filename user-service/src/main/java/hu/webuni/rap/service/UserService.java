package hu.webuni.rap.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.rap.dto.LoginDto;
import hu.webuni.rap.model.WebshopUser;
import hu.webuni.rap.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	public static final String LOGGED_USER_ID = "me";
	public static final String USER_FIELD_ID = "id";
	public static final String USER_FIELD_EMAIL = "email";
	public static final String USER_FIELD_FIRST_NAME = "first_name";
	public static final String USER_FIELD_LAST_NAME = "last_name";	
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public WebshopUser regWithUsername(LoginDto login){
		Optional<WebshopUser> findById = repository.findById(login.getUsername());
		if (findById.isEmpty()) {
			WebshopUser user = new WebshopUser(login.getUsername()
					, passwordEncoder.encode(login.getPassword())
					, Set.of("costumer")
					, login.getEmail()
					, login.getFbToken());
			return repository.save(user);
			
		}else {
			return null;
		}
	}

	public WebshopUser regWithFacebook(String facebookToken) {
		WebshopUser responseUser = null;
		Facebook facebook = new FacebookTemplate(facebookToken);
		User facebookUser = facebook.fetchObject(LOGGED_USER_ID, User.class, USER_FIELD_ID, USER_FIELD_EMAIL,
	            USER_FIELD_FIRST_NAME, USER_FIELD_LAST_NAME);
		String	facebookUserId = facebookUser.getId();
		String email = facebookUser.getEmail();
		String realName = facebookUser.getLastName()+" ";
		if (facebookUser.getMiddleName()!=null)
			realName = realName +facebookUser.getMiddleName()+" ";
		realName = realName + facebookUser.getFirstName();
		Optional<WebshopUser> webshopUser = repository.findById(realName);
		if (webshopUser.isEmpty()) {
			responseUser = new WebshopUser(email, passwordEncoder.encode("dummy"), Set.of("costumer"), email, facebookUserId);
			try {
				repository.save(responseUser);
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		return responseUser;
	}

	public String createJwtTokenFromFbToken(String fbToken) {
		Facebook facebook = new FacebookTemplate(fbToken);
		User facebookUser = facebook.fetchObject(LOGGED_USER_ID, User.class, USER_FIELD_ID, USER_FIELD_EMAIL,
	            USER_FIELD_FIRST_NAME, USER_FIELD_LAST_NAME);
		String	facebookUserId = facebookUser.getId();
		WebshopUser webshopUser = repository.findByFacebookId(facebookUserId).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.FORBIDDEN));
		
		return webshopUser.getUsername();
	}

	
}
