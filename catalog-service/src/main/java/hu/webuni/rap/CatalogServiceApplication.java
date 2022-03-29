package hu.webuni.rap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import hu.webuni.rap.service.InitDbService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CatalogServiceApplication implements CommandLineRunner {
	
	private final InitDbService initDdService;
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(CatalogServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDdService.checkDatasAndCreateIfNeed();
	}

}
