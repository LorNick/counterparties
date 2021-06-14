package ru.krivko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krivko.entity.Counterparty;
import ru.krivko.repository.CounterpartyRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис контрагента
 */
@Service
public class CounterpartyService {

    @Autowired
    private CounterpartyRepository counterpartyRepository;

    /**
     * Возвращает список всех имеющихся контрагентов
     *
     * @return список контрагентов
     */
    public List<Counterparty> readAll() {
        return counterpartyRepository.findAll();
    }

    /**
     * Возвращает 1го контрагента
     *
     * @param id - id контрагента
     * @return список контрагентов
     */
    public Optional<Counterparty> read(long id) {
        return counterpartyRepository.findById(id);
    }

    /**
     * Создание контрагента
     *
     * @param counterparty - новый контрагент
     */
    public void create(Counterparty counterparty) {
        counterpartyRepository.save(counterparty);
    }

    /**
     * Изменение контрагента
     * * @param id - id изменяемого контрагента
     *
     * @param counterparty - новые данные контрагента
     */
    public boolean update(long id, Counterparty counterparty) {
        Counterparty currentCounterparty = counterpartyRepository.findById(id).orElse(null);
        if (currentCounterparty == null) {
            return false;
        }
        currentCounterparty.setName(counterparty.getName());
        currentCounterparty.setInn(counterparty.getInn());
        currentCounterparty.setKpp(counterparty.getKpp());
        currentCounterparty.setAccountNumber(counterparty.getAccountNumber());
        currentCounterparty.setBic(counterparty.getBic());
        try {


        counterpartyRepository.saveAndFlush(counterparty);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Удаление контрагента
     *
     * @param id - удаляемый контрагент
     */
    public boolean delete(long id) {
        Counterparty currentCounterparty = counterpartyRepository.findById(id).orElse(null);
        if (currentCounterparty == null) {
            return false;
        }
        counterpartyRepository.deleteById(id);
        return true;
    }

    /**
     * Поиск контрагента с указанным именем
     *
     * @param name - имя контрагента
     * @return список контрагентов
     */
    public List<Counterparty> searchName(String name) {
        return counterpartyRepository.findFirstByName(name);
    }

    /**
     * Поиск контрагента по БИК и номеру аккаунта
     *
     * @param bic           - БИК
     * @param accountNumber - номер аккаунта
     * @return список контрагентов
     */
    public List<Counterparty> searchBicAndAccountNumber(String bic, String accountNumber) {
        return counterpartyRepository.findFirstByBicAndAccountNumber(bic, accountNumber);
    }

}
