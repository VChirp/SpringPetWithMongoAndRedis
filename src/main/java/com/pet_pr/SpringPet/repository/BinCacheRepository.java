package com.pet_pr.SpringPet.repository;

import com.pet_pr.SpringPet.entity.redis.BinCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinCacheRepository extends CrudRepository<BinCache, String> {
    Optional<BinCache> findBinCacheByBin(String bin);
}
