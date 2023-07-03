package dev.umc.whereseat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WhereseatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereseatApplication.class, args);
	}

}
