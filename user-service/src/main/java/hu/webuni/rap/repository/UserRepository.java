package hu.webuni.rap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.rap.model.WebshopUser;

public interface UserRepository extends JpaRepository<WebshopUser, String> {

}
