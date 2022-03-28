package hu.webuni.rap.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.rap.dto.LoginDto;
import hu.webuni.rap.model.WebshopUser;
import hu.webuni.rap.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
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

	
}
