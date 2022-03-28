package hu.webuni.rap.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.webuni.rap.model.WebshopUser;
import hu.webuni.rap.model.WebshopUserDetails;
import hu.webuni.rap.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WebshopUserDetailsService implements UserDetailsService {

	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<WebshopUser> user = repository.findById(username); 
		WebshopUserDetails info = new WebshopUserDetails(user.get().getUsername()
								, user.get().getPassword()
								, user.get().getRoles().stream().map(SimpleGrantedAuthority::new)
									.collect(Collectors.toList())
								, user.get().getEmail()
								, user.get().getFacebookId());
		return info;
	}

}
