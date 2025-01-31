package com.example.kastoriaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.kastoriaServer")
@EnableAutoConfiguration
public class KastoriaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KastoriaServerApplication.class, args);
	}

}
