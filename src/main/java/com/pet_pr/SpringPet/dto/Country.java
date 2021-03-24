package com.pet_pr.SpringPet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;

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
@Entity
@Table(name = "COUNTRY")
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("numeric")
    private String numeric;

    @JsonProperty("alpha2")
    private String alpha2;

    @JsonProperty("name")
    @Column(unique = true)
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
