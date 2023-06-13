package com.pet_pr.SpringPet.entity.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bin",
        "number",
        "scheme",
        "type",
        "brand",
        "prepaid",
        "country",
        "bank"
})
@Document("card_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInfo implements Serializable {

    @Id
    private String id;

    @JsonProperty("bin")
    private String bin;

    @JsonProperty("number")
    private NumberValues numberValues;

    @JsonProperty("scheme")
    private String scheme;

    @JsonProperty("type")
    private String type;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("prepaid")
    private Boolean prepaid;

    @JsonProperty("country")
    private Country country;

    @JsonProperty("bank")
    private Bank bank;
}
