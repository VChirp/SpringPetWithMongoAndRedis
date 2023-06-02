package com.pet_pr.SpringPet.controller;

import com.pet_pr.SpringPet.dto.CardInfo;
import com.pet_pr.SpringPet.exception.BusinessException;
import com.pet_pr.SpringPet.service.BinApiService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/bin")
public class BinApiController {

    final BinApiService binApiService;

    public BinApiController(BinApiService binApiService) {
        this.binApiService = binApiService;
    }

    @PutMapping("/calculate")
    public ResponseEntity<CardInfo> calculate(@RequestParam(value = "bin") String bin) throws BusinessException {
        CardInfo cardInfoByBin = binApiService.getCardInfoByBin(bin);

        return ResponseEntity.ok(cardInfoByBin);

    }

    @GetMapping("/cardInfo/{id}")
    public ResponseEntity<CardInfo> getCardInfoById(@PathVariable(value = "id") String id) {
        CardInfo cardInfoById = binApiService.getCardInfoByIdFromDb(id);

        return ResponseEntity.ok(cardInfoById);
    }

    @PostMapping("/bin_array")
    public ResponseEntity<List<CardInfo>> calculateBinArray(@RequestBody List<String> bins) throws BusinessException {
        List<CardInfo> cardInfoById = binApiService.getCardInfoByBinsArray(bins);

        return ResponseEntity.ok(cardInfoById);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CardInfo>> getAllCardInfos() {
        Page<CardInfo> allCardInfoFromDb = binApiService.getAllCardInfoFromDb();

        return ResponseEntity.ok(allCardInfoFromDb);
    }

    @GetMapping("/country_filter")
    public ResponseEntity<Page<CardInfo>> getAllCardInfoByCounty(@RequestParam(value = "country") String country) {
        Page<CardInfo> allCardInfoFromDbByCountry = binApiService.getAllCardInfoFromDbByCountry(country);

        return ResponseEntity.ok(allCardInfoFromDbByCountry);
    }
}