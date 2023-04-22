package sqa.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class PtitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtitApplication.class, args);
	}

}
