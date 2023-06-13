package com.pet_pr.SpringPet.entity.redis;

import com.pet_pr.SpringPet.entity.mongo.CardInfo;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("BinCache")
@Data
public class BinCache implements Serializable {
    private String id;
    private String bin;
    private CardInfo cardInfo;
}
