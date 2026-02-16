package com.alfredogarcia.evaluacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiTransaccionValidadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransaccionValidadorApplication.class, args);
	}

}
