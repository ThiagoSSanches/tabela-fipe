package start.spring.io.buyacar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import start.spring.io.buyacar.main.Main;
import start.spring.io.buyacar.service.ConsumingApi;

@SpringBootApplication
public class BuyacarApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BuyacarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.displayingMenu();
	}
}
