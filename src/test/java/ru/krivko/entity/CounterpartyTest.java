package ru.krivko.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверяем серелизацию/десерелизацию модели
 */
@JsonTest
class CounterpartyTest {
    @Autowired
    private JacksonTester<Counterparty> jacksonTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialize() throws IOException {
        assertNotNull(objectMapper);

        Counterparty counterparty = new Counterparty() {{
            setId(40L); setName("ВТБ"); setInn("7731347089"); setKpp("123456789");
            setAccountNumber("30101810100000000835"); setBic("042007835");
        }};

        JsonContent<Counterparty> result = jacksonTester.write(counterparty);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(40);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ВТБ");
        assertThat(result).extractingJsonPathStringValue("$.inn").isEqualTo("7731347089");
        assertThat(result).extractingJsonPathStringValue("$.kpp").isEqualTo("123456789");
        assertThat(result).extractingJsonPathStringValue("$.accountNumber").isEqualTo("30101810100000000835");
        assertThat(result).extractingJsonPathStringValue("$.bic").isEqualTo("042007835");
    }

    @Test
    public void testDeserialize() throws IOException {
        assertNotNull(objectMapper);
        String jsonInString = "{\"id\": 40,\"name\": \"ВТБ\",\"inn\": \"7731347089\",\"kpp\": \"123456789\"," +
                "\"accountNumber\": \"30101810100000000835\",\"bic\": \"042007835\"}";
        Counterparty counterparty = objectMapper.readValue(jsonInString, Counterparty.class);
        assertEquals(40L, counterparty.getId());
        assertEquals("ВТБ", counterparty.getName());
        assertEquals("7731347089", counterparty.getInn());
        assertEquals("123456789", counterparty.getKpp());
        assertEquals("30101810100000000835", counterparty.getAccountNumber());
        assertEquals("042007835", counterparty.getBic());
    }
}