package hu.webuni.rap.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hu.webuni.rap.dto.LoginDto;

@FeignClient(name="users", url = "${feign.users.url}")
public interface UsersApi {
	
    @PostMapping("/login")
    String login(@RequestBody LoginDto login);
    
    @PostMapping("/register/user")
    void userRegister(@RequestBody LoginDto login);
    
    @PostMapping("/register/facebook")
    void userRegister(@RequestBody String facebookToken);
}
