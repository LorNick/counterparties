package ru.krivko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.krivko.entity.Counterparty;

import java.util.List;

/**
 * Репозиторий контрагента
 */
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    List<Counterparty> findFirstByName(String name);

    List<Counterparty> findFirstByBicAndAccountNumber(String bic, String accountNumber);
}