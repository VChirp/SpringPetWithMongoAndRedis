package com.pet_pr.SpringPet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet_pr.SpringPet.dto.CardInfo;
import com.pet_pr.SpringPet.service.BinApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.pet_pr.SpringPet.StubGenerator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BinApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BinApiService binApiService;

    @Test
    public void calculateTest() throws Exception {

        given(binApiService.getCardInfoByBin(TEST_BIN)).willReturn(generateCardInfo());

        this.mockMvc.perform(put("/calculate")
                .param("bin", TEST_BIN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bin", is(TEST_BIN)));
    }

    @Test
    public void getCardInfoByIdTest() throws Exception {
        String id = "1";

        given(binApiService.getCardInfoByIdFromDb(id)).willReturn(generateCardInfo());

        this.mockMvc.perform(get("/cardInfo/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateBinArrayTest() throws Exception {

        given(binApiService.getCardInfoByBinsArray(TEST_BINS)).willReturn(generateListOfCardInfos());

        this.mockMvc.perform(post("/bin_array")
                .content(asJsonString(TEST_BINS))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bin", is(TEST_BINS.get(0))))
                .andExpect(jsonPath("$[1].bin", is(TEST_BINS.get(1))));
    }

    @Test
    public void getAllCardInfosTest() throws Exception {

        Page<CardInfo> cardInfoPage = new PageImpl<>(generateListOfCardInfos());

        given(binApiService.getAllCardInfoFromDb()).willReturn(cardInfoPage);

        this.mockMvc.perform(get("/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].bin", is(TEST_BINS.get(0))))
                .andExpect(jsonPath("$.content[1].bin", is(TEST_BINS.get(1))));
    }

    @Test
    public void getAllCardInfosByCountryTest() throws Exception {

        Page<CardInfo> cardInfoPage = new PageImpl<>(generateListOfCardInfos());

        given(binApiService.getAllCardInfoFromDbByCountry(UNITED_STATES_OF_AMERICA)).willReturn(cardInfoPage);

        this.mockMvc.perform(get("/country_filter")
                .param("country", UNITED_STATES_OF_AMERICA)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].country.name", is(UNITED_STATES_OF_AMERICA)))
                .andExpect(jsonPath("$.content[1].country.name", is(UNITED_STATES_OF_AMERICA)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

