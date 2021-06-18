package ru.krivko.controiller;

import ru.krivko.entity.Counterparty;

import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * Тестируем REST API контроллер, с запущенным сервером
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CounterpartyRESTControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String UPDATE_NAME = "ОАО Тест Изменил";

    private static final String DEFAULT_NAME = "ОАО Тест Создал";
    private static final String DEFAULT_INN = "7731347089";
    private static final String DEFAULT_KOP = "123456789";
    private static final String DEFAULT_ACCOUNT_NUMBER = "30101810450040000719";
    private static final String DEFAULT_BIC = "045004719";
    private static final Counterparty DEFAULT_COUNTERPARTY = new Counterparty(DEFAULT_NAME,
            DEFAULT_INN, DEFAULT_KOP, DEFAULT_ACCOUNT_NUMBER, DEFAULT_BIC);

    /**
     * Создаем новую тестовую запись и возвращаем её
     */
    public Counterparty createTestRecordCounterparty() {
        return restTemplate.postForEntity("/api/counterparties/", DEFAULT_COUNTERPARTY, Counterparty.class).getBody();
    }

    /**
     * Удаляем тестовую запись по id
     */
    void deleteTestRecordCounterparty(long id) {
        restTemplate.delete("/api/counterparties/delete_id?id=" + id);
    }

    @Test
    void create() {
        ResponseEntity<Counterparty> response = restTemplate.postForEntity("/api/counterparties/",
                DEFAULT_COUNTERPARTY, Counterparty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        deleteTestRecordCounterparty(response.getBody().getId());
    }

    @Test
    void getAll() {
        long id = createTestRecordCounterparty().getId();

        ResponseEntity<Counterparty[]> response = restTemplate.getForEntity(
                "/api/counterparties/get_all", Counterparty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        deleteTestRecordCounterparty(id);
    }

    @Test
    void getId() {
        long id = createTestRecordCounterparty().getId();

        ResponseEntity<Counterparty> response = restTemplate.getForEntity(
                "/api/counterparties/get_id?id=" + id, Counterparty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        deleteTestRecordCounterparty(id);
    }

    @Test
    void getName() {
        long id = createTestRecordCounterparty().getId();

        ResponseEntity<Counterparty> response = restTemplate.getForEntity(
                "/api/counterparties/get_name?name=" + DEFAULT_NAME, Counterparty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        deleteTestRecordCounterparty(id);
    }

    @Test
    void getAccountNumberAndBic() {
        long id = createTestRecordCounterparty().getId();

        ResponseEntity<Counterparty[]> response = restTemplate.getForEntity(
                "/api/counterparties/get_anb?accountNumber=" + DEFAULT_ACCOUNT_NUMBER + "&bic=" + DEFAULT_BIC, Counterparty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        deleteTestRecordCounterparty(id);
    }

    @Test
    void update() {
        Counterparty counterparty = createTestRecordCounterparty();
        long id = counterparty.getId();
        counterparty.setName(UPDATE_NAME);

        RequestEntity<Counterparty> requestEntity = RequestEntity
                .put("/api/counterparties/")
                .body(counterparty);
        ResponseEntity<Counterparty> response = restTemplate.exchange(requestEntity, Counterparty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(UPDATE_NAME);

        deleteTestRecordCounterparty(id);
    }

    @Test
    void deleteId() {
        long id = createTestRecordCounterparty().getId();

        ResponseEntity<Integer> result = restTemplate.exchange("/api/counterparties/delete_id?id=" + id,
                HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class, id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteName() {
        createTestRecordCounterparty();

        ResponseEntity<Integer> result = restTemplate.exchange("/api/counterparties/delete_name?name=" + DEFAULT_NAME,
                HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class, DEFAULT_NAME);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}