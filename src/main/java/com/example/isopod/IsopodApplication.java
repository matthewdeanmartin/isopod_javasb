package com.example.isopod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({GameController.class })
public class IsopodApplication {

	// logging.level.root=warn
	//logging.level.org.springframework.web=debug
	// silence console logging
//	@Value("${logging.level.root:debug}")
//	String message = "";

	public static void main(String[] args) {
		SpringApplication.run(IsopodApplication.class, args);
	}

}
