package com.pet_pr.SpringPet;

import com.pet_pr.SpringPet.repository.BankMongoRepository;
import com.pet_pr.SpringPet.repository.CardInfoMongoRepository;
import com.pet_pr.SpringPet.repository.CountryMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SpringPetApplicationTests {

	@MockBean
	private CardInfoMongoRepository cardInfoRepository;

	@MockBean
	private BankMongoRepository bankMongoRepository;

	@MockBean
	private CountryMongoRepository countryMongoRepository;

	@Test
	void contextLoads() {
	}

}
