package ru.krivko.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class CounterpartyTest {
    @Autowired
    private JacksonTester<Counterparty> json;

    @Test
    public void testToDto() throws IOException {
        Counterparty counterparty = new Counterparty() {{
            setId(40L); setName("ВТБ"); setInn("7731347089"); setKpp("123456789");
            setAccountNumber("30101810100000000835"); setBic("042007835");
        }};
        assertThat(this.json.write(counterparty))
                .isStrictlyEqualToJson("counterparty.json");
        System.out.println("11111111111");
    }
}