package hu.webuni.rap.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.rap.api.UsersApi;
import hu.webuni.rap.dto.LoginDto;
import hu.webuni.rap.service.UserService;
import hu.webuni.rap.tokenlib.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestControllers implements UsersApi{
	
	private final JwtService jwtService;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;


	@Override
	public String login(LoginDto login) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		
		return "\"" + jwtService.creatJwtToken((UserDetails)authentication.getPrincipal()) + "\"";
	}

	@Override
	public void userRegister(LoginDto login) {
		if (userService.regWithUsername(login)==null) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The entered username is busy!");
		}
	}

	@Override
	public void userRegister(String facebookToken) {
		// TODO Auto-generated method stub
	}

}
