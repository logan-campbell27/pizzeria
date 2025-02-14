package StrongMind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"StrongMind"})
@SpringBootApplication
public class Pizzeria {

	public static void main(String[] args) {
		SpringApplication.run(Pizzeria.class, args);
	}

}
