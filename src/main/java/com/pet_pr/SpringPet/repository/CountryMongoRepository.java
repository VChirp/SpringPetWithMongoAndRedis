package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.entity.mongo.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryMongoRepository extends MongoRepository<Country, Long> {

    Country findCountryByName(String name);
}
