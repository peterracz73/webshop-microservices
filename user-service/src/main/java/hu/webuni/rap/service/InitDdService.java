package hu.webuni.rap.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.rap.model.WebshopUser;
import hu.webuni.rap.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitDdService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	
	public void deleteTestUser() {
		Optional<WebshopUser> testUser = repository.findById("Rácz Péter");
		if (testUser.isPresent()) {
			repository.delete(testUser.get());
		}
	}
	
	public void CreateAdminIfNeed() {
		Optional<WebshopUser> admin = repository.findById("admin");
		if (admin.isEmpty()) {
			WebshopUser adminUser = new WebshopUser("admin", passwordEncoder.encode("pass")
					, Set.of("admin", "costumer") , "peter.racz73@gmail.com", null);
			repository.save(adminUser);
		}
	}
}
