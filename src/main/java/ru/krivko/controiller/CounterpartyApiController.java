package ru.krivko.controiller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import ru.krivko.entity.Counterparty;
import ru.krivko.exception.NotFoundException;
import ru.krivko.service.CounterpartyService;

/**
 * REST API контроллер контрагента
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/counterparties")
public class CounterpartyApiController {

    @Autowired
    private final CounterpartyService counterpartyService;

    /**
     * Создание нового контрагента
     *
     * @param counterparty новый контрагент
     * @return возврат код 201, в случае ошибки сервер выдает код 500 или 400
     */
    @PostMapping
    public ResponseEntity<Counterparty> create(@RequestBody Counterparty counterparty) {
        counterpartyService.create(counterparty);
        log.info("**** Создаем нового контрагента");
        return new ResponseEntity<>(counterparty, HttpStatus.CREATED);
    }

    /**
     * Получаем список всех контрагентов
     *
     * @return в случае успеха получаем список контрагентов и код 200, иначе код 404
     */
    @GetMapping("get_all")
    public ResponseEntity<List<Counterparty>> getAll() {
        List<Counterparty> counterpartyList = counterpartyService.readAll();
        if (counterpartyList != null && !counterpartyList.isEmpty()) {
            log.info("**** Находим все контрагенты");
            return new ResponseEntity<>(counterpartyList, HttpStatus.OK);
        }
        throw new NotFoundException("Нет ни одного контрагента");
    }

    /**
     * Получаем контрагент по id
     *
     * @param id код контрагента
     * @return в случае успеха получаем контрагента и код 200, иначе код 404
     */
    @GetMapping("get_id")
    public ResponseEntity<Counterparty> getId(@RequestParam long id) {
        var counterparty = counterpartyService.read(id).orElse(null);
        if (counterparty != null) {
            log.info("**** Находим контрагент по id = " + id);
            return new ResponseEntity<>(counterparty, HttpStatus.OK);
        }
        throw new NotFoundException("Не удалось найти контрагент по id = " + id);
    }

    /**
     * Возвращаем контрагент по имени
     *
     * @param name имя контрагента
     * @return в случае успеха получаем список контрагентов и код 200, иначе код 404
     */
    @GetMapping("get_name")
    public ResponseEntity<Counterparty> getName(@RequestParam String name) {
        List<Counterparty> counterpartyList = counterpartyService.searchName(name);
        if (counterpartyList != null && !counterpartyList.isEmpty()) {
            log.info("**** Находим контрагент по имени = " + name);
            return new ResponseEntity<>(counterpartyList.get(0), HttpStatus.OK);
        }
        throw new NotFoundException("Не удалось найти контрагент по name = " + name);
    }

    /**
     * Возвращаем контрагент по номеру счета и БИК
     *
     * @param accountNumber номер счета контрагента
     * @param bic           БИК контрагента
     * @return в случае успеха получаем список контрагентов и код 200, иначе код 404
     */
    @GetMapping("get_anb")
    public ResponseEntity<List<Counterparty>> getAccountNumberAndBic(
            @RequestParam String accountNumber, @RequestParam String bic) {
        List<Counterparty> counterpartyList = counterpartyService.searchBicAndAccountNumber(bic, accountNumber);
        if (counterpartyList != null && !counterpartyList.isEmpty()) {
            log.info("**** Находим контрагент по номеру счета = " + accountNumber + " и БИК = " + bic);
            return new ResponseEntity<>(counterpartyList, HttpStatus.OK);
        }
        throw new NotFoundException("Не удалось найти контрагент по номеру счета = " + accountNumber + " и БИК = " + bic);
    }

    /**
     * Изменяем контрагент
     *
     * @param counterparty json данные контрагента
     * @return в случае успеха получаем контрагента и код 200,
     *      иначе код 404 если не нашли что менять, или 400 если всё плохо
     */
    @PutMapping
    public ResponseEntity<Counterparty> update(@RequestBody @Valid Counterparty counterparty) {
        var counterpartyUpdate = counterpartyService.update(counterparty.getId(), counterparty);
        if (counterpartyUpdate != null) {
            log.info("**** Изменили контрагент с id = " + counterparty.getId());
            return new ResponseEntity<>(counterparty, HttpStatus.OK);
        }
        throw new NotFoundException("Не нашли контрагента с id = " + counterparty.getId()
                + " для того что бы его изменить");
    }

    /**
     * Удаляем контрагент по id
     *
     * @param id код контрагента
     * @return если нашли и удалили контрагент то код 200, иначе код 404
     */
    @DeleteMapping("/delete_id")
    public ResponseEntity<Counterparty> deleteId(@RequestParam long id) {
        if (counterpartyService.delete(id)) {
            log.info("**** Удаляем контрагента с id = " + id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException("Не удалось удалить контрагента с id = " + id);
    }

    /**
     * Удаляем контрагент по имени
     *
     * @param name имя контрагента
     * @return если нашли и удалили контрагент то код 200, иначе код 404
     */
    @DeleteMapping("/delete_name")
    public ResponseEntity<Counterparty> deleteName(@RequestParam String name) {
        List<Counterparty> counterpartyList = counterpartyService.searchName(name);
        if (counterpartyList != null && !counterpartyList.isEmpty()
                && counterpartyService.delete(counterpartyList.get(0).getId())) {
            log.info("**** Удаляем контрагента с name = " + name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException("Не удалось удалить контрагента с name = " + name);
    }
}