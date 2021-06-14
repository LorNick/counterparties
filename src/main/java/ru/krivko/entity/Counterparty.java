package ru.krivko.entity;

import lombok.*;
import org.hibernate.validator.constraints.ru.INN;
import ru.krivko.validate.ValidAccountNumberAnfBic;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Модель контрагента
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@ValidAccountNumberAnfBic // тут проверяем валидацию счета и бик
@Entity
@Table(name = "counterparty")
public class Counterparty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20, message = "Имя должно быть не более 20 символов")
    @Column(unique = true, nullable = false)
    private String name;

    @INN(message = "ИНН у вас какой то не правильный")
    @Column(nullable = false)
    private String inn;

    @Column
    @Pattern(regexp = "\\d{9}$", message = "Введите 9 цифр")
    private String kpp;

    @Pattern(regexp = "\\d{20}$", message = "Введите 20 цифр")
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Pattern(regexp = "\\d{9}$", message = "Введите 9 цифр")
    @Column(nullable = false)
    private String bic;
}