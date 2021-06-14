package ru.krivko;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.krivko.controiller.CounterpartyController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CounterpartyApplicationTests {

	@Autowired
	private CounterpartyController counterpartyController;

	@Test
	void contextLoads() {
		assertThat(counterpartyController).isNotNull();
	}
}
