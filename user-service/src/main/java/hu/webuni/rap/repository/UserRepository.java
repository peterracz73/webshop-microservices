package hu.webuni.rap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.rap.model.WebshopUser;

public interface UserRepository extends JpaRepository<WebshopUser, String> {

	Optional<WebshopUser> findByFacebookId(String facebookUserId);

}
