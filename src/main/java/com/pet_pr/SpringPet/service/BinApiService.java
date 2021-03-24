package com.pet_pr.SpringPet.service;

import com.pet_pr.SpringPet.dto.Bank;
import com.pet_pr.SpringPet.dto.CardInfo;
import com.pet_pr.SpringPet.dto.Country;
import com.pet_pr.SpringPet.exception.BusinessException;
import com.pet_pr.SpringPet.repository.BankRepository;
import com.pet_pr.SpringPet.repository.CardInfoRepository;
import com.pet_pr.SpringPet.repository.CountryRepository;
import com.pet_pr.SpringPet.utils.BinUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BinApiService {

    public static final String HTTPS_LOOKUP_BINLIST_NET = "https://lookup.binlist.net/%s";

    private final RestTemplate restTemplate;

    private final CardInfoRepository cardInfoRepository;

    private final BankRepository bankRepository;

    private final CountryRepository countryRepository;

    public static final Pageable PAGEABLE = PageRequest.of(0, 10);

    public BinApiService(RestTemplateBuilder restTemplateBuilder,
                         CardInfoRepository cardInfoRepository,
                         BankRepository bankRepository, CountryRepository countryRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.cardInfoRepository = cardInfoRepository;
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
    }

    public CardInfo getCardInfoByBin(String bin) throws BusinessException {

        if (!BinUtils.checkBinFormat(bin)) {
            throw new BusinessException("Wrong bin format");
        }

        String url = String.format(HTTPS_LOOKUP_BINLIST_NET, bin);

        CardInfo cardInfo = this.restTemplate.getForObject(url, CardInfo.class);

        if (cardInfo != null) {
            cardInfo.setBin(bin);
            cardInfoRepository.save(checkUniqueFields(cardInfo));
        }

        return cardInfo;
    }

    public List<CardInfo> getCardInfoByBinsArray(List<String> bins) throws BusinessException {
        List<CardInfo> cardInfoList = new ArrayList<>();

        for (String bin : bins) {
            cardInfoList.add(getCardInfoByBin(bin));
        }

        return cardInfoList;
    }

    private CardInfo checkUniqueFields(CardInfo cardInfo) {
        if (cardInfo.getBank() != null) {

            Bank bank = bankRepository.findBankByName(cardInfo.getBank().getName());
            Country country = countryRepository.findCountryByName(cardInfo.getCountry().getName());

            if (bank != null) {
                cardInfo.setBank(bank);
            }

            if (country != null) {
                cardInfo.setCountry(country);
            }
        }

        return cardInfo;
    }

    public CardInfo getCardInfoByIdFromDb(Long id) {
        Optional<CardInfo> byId = cardInfoRepository.findById(id);

        return byId.orElse(null);
    }

    public Page<CardInfo> getAllCardInfoFromDb() {

        return cardInfoRepository.findAll(PAGEABLE);
    }

    public Page<CardInfo> getAllCardInfoFromDbByCountry(String country) {

        return cardInfoRepository.findAllByCountry_Name(country, PAGEABLE);
    }
}
