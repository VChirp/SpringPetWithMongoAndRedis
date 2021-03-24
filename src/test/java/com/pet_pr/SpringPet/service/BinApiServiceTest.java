package com.pet_pr.SpringPet.service;

import com.pet_pr.SpringPet.dto.CardInfo;
import com.pet_pr.SpringPet.exception.BusinessException;
import com.pet_pr.SpringPet.repository.CardInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.pet_pr.SpringPet.StubGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BinApiServiceTest {

    public static final String BANK_OF_AMERICA_N_A = "BANK OF AMERICA, N.A.";

    @Autowired
    BinApiService binApiService;

    @MockBean
    private CardInfoRepository cardInfoRepository;

    @Test
    public void getCardInfoByBinTest() throws BusinessException {

        CardInfo cardInfoByBin = binApiService.getCardInfoByBin(TEST_BIN);

        assertNotNull(cardInfoByBin);
        assertNotNull(cardInfoByBin.getCountry());
        assertNotNull(cardInfoByBin.getBank());

        assertEquals(cardInfoByBin.getBin(), TEST_BIN);
        assertEquals(cardInfoByBin.getCountry().getName(), UNITED_STATES_OF_AMERICA);
        assertEquals(cardInfoByBin.getBank().getName(), BANK_OF_AMERICA_N_A);
    }

    @Test
    public void getCardInfoByBinTest_withWrongBinFormat() {

        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            binApiService.getCardInfoByBin("1-24-6");
        });

        String expectedErrorMessage = "Wrong bin format";
        String actualErrorMessage = businessException.getMessage();

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void getCardInfoByBinsArray() throws BusinessException {

        List<CardInfo> binsArray = binApiService.getCardInfoByBinsArray(TEST_BINS);

        assertNotNull(binsArray);
        assertFalse(binsArray.isEmpty());
        assertEquals(2, binsArray.size());

        CardInfo cardInfo = binsArray.get(1);

        assertEquals(TEST_BINS.get(1), cardInfo.getBin());
    }

    @Test
    public void getCardInfoByIdFromDb() {
        Long id = 1L;

        given(cardInfoRepository.findById(id)).willReturn(Optional.of(generateCardInfo()));

        CardInfo cardInfo = binApiService.getCardInfoByIdFromDb(id);

        assertNotNull(cardInfo);
        assertEquals(TEST_BIN, cardInfo.getBin());
    }

    @Test
    public void getAllCardInfoFromDb() {

        Page<CardInfo> cardInfoExpectedPage = new PageImpl<>(generateListOfCardInfos());

        given(cardInfoRepository.findAll(any())).willReturn(cardInfoExpectedPage);

        Page<CardInfo> cardInfoActualPage = binApiService.getAllCardInfoFromDb();

        assertNotNull(cardInfoActualPage);

        CardInfo cardInfo = cardInfoActualPage.get().findFirst().get();
        assertEquals(TEST_BINS.get(0), cardInfo.getBin());
    }

    @Test
    public void getAllCardInfoFromDbByCountry() {

        Page<CardInfo> cardInfoExpectedPage = new PageImpl<>(generateListOfCardInfos());

        given(cardInfoRepository.findAllByCountry_Name(eq(UNITED_STATES_OF_AMERICA), any()))
                .willReturn(cardInfoExpectedPage);


        Page<CardInfo> cardInfoActualPage = binApiService.getAllCardInfoFromDbByCountry(UNITED_STATES_OF_AMERICA);
        assertNotNull(cardInfoActualPage);

        CardInfo cardInfo = cardInfoActualPage.get().findFirst().get();
        String countryName = cardInfo.getCountry().getName();

        assertEquals(UNITED_STATES_OF_AMERICA, countryName);
    }
}
