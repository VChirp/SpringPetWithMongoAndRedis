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
        "length",
        "luhn"
})
@Document("number_values")
@Data
public class NumberValues implements Serializable {

    @Id
    private String id;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("luhn")
//    @Basic
    private Boolean luhn;
}
