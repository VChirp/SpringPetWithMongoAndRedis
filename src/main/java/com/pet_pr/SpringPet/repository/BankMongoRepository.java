package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.entity.mongo.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankMongoRepository extends MongoRepository<Bank, Long> {
    Bank findBankByName(String name);
}
