package com.pet_pr.SpringPet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = CardInfoMongoRepository.class)
public class SpringPetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPetApplication.class, args);
	}

}
