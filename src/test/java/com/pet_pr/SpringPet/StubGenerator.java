package com.pet_pr.SpringPet;

import com.pet_pr.SpringPet.entity.mongo.CardInfo;
import com.pet_pr.SpringPet.entity.mongo.Country;

import java.util.ArrayList;
import java.util.List;

public class StubGenerator {

    public static final String UNITED_STATES_OF_AMERICA = "United States of America";

    public static String TEST_BIN = "4916533748629412";

    public static final List<String> TEST_BINS = new ArrayList<>() {{
        add("4916534930454379");
        add("4916531364524826");
    }};

    public static CardInfo generateCardInfo() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setId("1");
        cardInfo.setBin(TEST_BIN);

        return cardInfo;
    }

    public static List<CardInfo> generateListOfCardInfos() {
        Country country = new Country();
        country.setId("1");
        country.setName(UNITED_STATES_OF_AMERICA);

        CardInfo cardInfoOne = new CardInfo();
        cardInfoOne.setId("1");
        cardInfoOne.setBin(TEST_BINS.get(0));
        cardInfoOne.setCountry(country);

        CardInfo cardInfoTwo = new CardInfo();
        cardInfoTwo.setId("2");
        cardInfoTwo.setBin(TEST_BINS.get(1));
        cardInfoTwo.setCountry(country);

        ArrayList<CardInfo> cardInfos = new ArrayList<>();
        cardInfos.add(cardInfoOne);
        cardInfos.add(cardInfoTwo);

        return cardInfos;
    }
}
