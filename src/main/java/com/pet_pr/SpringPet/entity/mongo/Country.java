package com.pet_pr.SpringPet.entity.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "numeric",
        "alpha2",
        "name",
        "emoji",
        "currency",
        "latitude",
        "longitude"
})
@Document("country")
@Data
public class Country implements Serializable {
    @Id
    private String id;

    @JsonProperty("numeric")
    private String numeric;

    @JsonProperty("alpha2")
    private String alpha2;

    @JsonProperty("name")
    private String name;

    @JsonProperty("emoji")
    private String emoji;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("latitude")
    private Integer latitude;

    @JsonProperty("longitude")
    private Integer longitude;
}
