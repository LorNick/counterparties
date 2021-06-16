package ru.krivko.controiller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import ru.krivko.entity.Counterparty;
import ru.krivko.service.CounterpartyService;

/**
 * Web контроллер контрагента
 */
@Slf4j
@Controller
@RequestMapping("counterparties")
public class CounterpartyWebController {

    @Autowired
    private CounterpartyService counterpartyService;

    /**
     * Начальная страница контрагентов
     *
     * @param model модель
     * @return путь к начальной странице
     */
    @GetMapping
    public String counterpartiesPage(Model model) {
        model.addAttribute("counterparties", counterpartyService.readAll());
        log.info("**** Отображаем начальную страницу контрагентов");
        return "counterparties";
    }

    /**
     * Страница создания нового контрагента
     *
     * @param model модель
     * @return путь к странице создания нового контрагента
     */
    @GetMapping("/new")
    public String newCounterpartiesPage(Model model) {
        Counterparty counterparty = new Counterparty();
        model.addAttribute("command", counterparty);
        log.info("**** Отображаем страницу создания контрагентов");
        return "counterpartyNew";
    }

    /**
     * Создание нового контрагента
     *
     * @param counterparty новый контрагент
     * @return возврат к начальной странице
     */
    @PostMapping("/new")
    public String createCounterparty(@Valid @ModelAttribute("command") Counterparty counterparty,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("**** При создании контрагента, не прошли валидацию");
            return "counterpartyNew";
        }
        log.info("**** Создали нового контрагента");
        counterpartyService.create(counterparty);
        return "redirect:/counterparties";
    }

    /**
     * Удаление контрагента
     *
     * @param id код удаляемого контрагента
     * @return возврат к начальной странице
     */
    @GetMapping("/delete")
    public String deleteCounterparty(@RequestParam long id) {
        counterpartyService.delete(id);
        log.info("**** Удаляем контрагента с id = " + id);
        return "redirect:/counterparties";
    }

    /**
     * Страница изменения контрагента
     *
     * @param id    код изменяемого контрагента
     * @param model модель
     * @return возврат к начальной странице
     */
    @GetMapping("/update")
    public String updateCounterpartyPage(@RequestParam long id, Model model) {
        log.info("**** Отображаем страницу редактирования контрагентов");
        model.addAttribute("command", counterpartyService.read(id).orElse(null));
        return "counterpartyUpdate";
    }

    /**
     * Изменение контрагента
     *
     * @param counterparty изменяемый контрагент
     * @return путь к странице изменения контрагента
     */
    @PostMapping("/update")
    public String updateCounterparty(@Valid @ModelAttribute("command") Counterparty counterparty,
                                     BindingResult bindingResult, Model model) {
        log.info("**** Попытались зафиксировать редактируемого контрагента");
        if (bindingResult.hasErrors()) {
            log.info("**** При редактирования контрагента, не прошли валидацию");
            return "counterpartyUpdate";
        }
        if (counterpartyService.update(counterparty.getId(), counterparty)) {
            return "redirect:/counterparties";
        } else {
            return "counterpartyUpdate";
        }
    }

    /**
     * Поиск по имени контрагентов
     *
     * @param name  имя контрагента
     * @param model модель
     * @return начальная страница
     */
    @GetMapping("/search")
    public String searchCounterpartyName(@RequestParam String name, Model model) {
        log.info("**** Ищем контрагента с именем = " + name);
        model.addAttribute("counterparties", counterpartyService.searchName(name));
        return "counterparties";
    }

    /**
     * Поиск по номеру счета и БИК контрагента
     *
     * @param accountNumber номер счета контрагента
     * @param bic           БИК контрагента
     * @param model         модель
     * @return начальная страница
     */
    @GetMapping("/search_two")
    public String searchCounterpartyBicAndAccountNumber(@RequestParam String accountNumber,
                                                        @RequestParam String bic, Model model) {
        log.info("**** Ищем контрагента с бик = " + bic + " и номером счета = " + accountNumber);
        model.addAttribute("counterparties",
                counterpartyService.searchBicAndAccountNumber(bic, accountNumber));
        return "counterparties";
    }
}