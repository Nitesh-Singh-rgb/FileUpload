package com.example.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.springapi.uploading"})
@EnableJpaRepositories(basePackages = {"com.springapi.uploading"})
@EntityScan(basePackages = {"com.springapi.uploading"})
public class FilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesApplication.class, args);
	}

}
