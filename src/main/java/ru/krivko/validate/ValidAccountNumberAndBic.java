package ru.krivko.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для валидации Номера счета и БИК
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAccountNumberAndBicValidator.class)
public @interface ValidAccountNumberAndBic {
    String message() default "Номер расчетного счета и БИК не валидны";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}