package com.rohan.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudInOneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudInOneApiApplication.class, args);
	}

}
