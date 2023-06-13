package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.entity.mongo.NumberValues;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumberMongoRepository extends MongoRepository<NumberValues, Long> {
}
