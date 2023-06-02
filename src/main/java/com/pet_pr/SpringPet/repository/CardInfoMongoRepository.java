package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.dto.CardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CardInfoMongoRepository extends MongoRepository<CardInfo, String> {

    Page<CardInfo> findAll(Pageable pageable);

    Page<CardInfo> findAllByCountry_Name(String country, Pageable pageable);
}
