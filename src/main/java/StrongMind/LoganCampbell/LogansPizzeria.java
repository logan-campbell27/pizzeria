package StrongMind.LoganCampbell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"StrongMind.LoganCampbell"})
@SpringBootApplication
public class LogansPizzeria {

	public static void main(String[] args) {
		SpringApplication.run(LogansPizzeria.class, args);
	}

}
