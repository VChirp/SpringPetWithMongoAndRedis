package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.NumberValues;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumberMongoRepository extends MongoRepository<NumberValues, Long> {
}
