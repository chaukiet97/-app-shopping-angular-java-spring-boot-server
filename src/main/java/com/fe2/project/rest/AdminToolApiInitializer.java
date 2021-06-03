package com.fe2.project.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableJpaRepositories
public class AdminToolApiInitializer {
    @Autowired

	public static void main(String[] args) {
		SpringApplication.run(AdminToolApiInitializer.class, args);
	}

	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "There") String name) {
		
		return String.format("Hi %s!", name);
	}

}
