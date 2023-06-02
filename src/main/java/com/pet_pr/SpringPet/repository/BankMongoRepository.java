package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankMongoRepository extends MongoRepository<Bank, Long> {
    Bank findBankByName(String name);
}
