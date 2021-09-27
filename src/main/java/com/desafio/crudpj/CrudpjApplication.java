package com.desafio.crudpj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrudpjApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudpjApplication.class, args);
	}

}
