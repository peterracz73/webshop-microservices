package hu.webuni.rap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import hu.webuni.rap.service.InitDdService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class UsersServiceApplication implements CommandLineRunner{

	private final InitDdService initDdService;
	public static ConfigurableApplicationContext context;
	
	public static void main(String[] args) {
		context = SpringApplication.run(UsersServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDdService.CreateAdminIfNeed();
		
	}

}
