package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Long> {

    Bank findBankByName(String name);
}
