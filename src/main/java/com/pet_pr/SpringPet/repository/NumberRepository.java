package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.NumberValues;
import org.springframework.data.repository.CrudRepository;

public interface NumberRepository extends CrudRepository<NumberValues, Long> {
}
