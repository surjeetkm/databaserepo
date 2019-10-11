package com.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.config","com.db"})
public class DatabaseAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseAwsApplication.class, args);
	}

}
