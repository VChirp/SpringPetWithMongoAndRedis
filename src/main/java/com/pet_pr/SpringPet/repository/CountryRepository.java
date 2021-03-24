package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findCountryByName(String name);
}
