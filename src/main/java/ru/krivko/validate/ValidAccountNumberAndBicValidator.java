package ru.krivko.validate;

import ru.krivko.entity.Counterparty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидация номера счета и БИК
 */
public class ValidAccountNumberAndBicValidator
        implements ConstraintValidator<ValidAccountNumberAnfBic, Counterparty> {
    @Override
    public void initialize(ValidAccountNumberAnfBic constraintAnnotation) {
    }

    @Override
    public boolean isValid(Counterparty counterparty, ConstraintValidatorContext context) {
        if (counterparty == null) {
            return false;
        }
        if (counterparty.getAccountNumber() == null || counterparty.getBic() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{ru.krivko.validate.validation.unique.itn}")
                    .addPropertyNode("accountNumber").addConstraintViolation();
            return false;
        }

        return valid(counterparty.getAccountNumber(), counterparty.getBic());
    }

    /**
     * Проверяем валидность номера счета и БИК
     * <br>1. Если последние 3 цифры у них совпадают и 3е цифры номера счета равны 301 - то это коррсчет
     * <br>2. Дополняем номер счета либо последними 3мя символами, либо 5 и 6 (для коррсчета) БИК
     * <br>3. Вычисляется контрольная сумма с весовыми коэффициентам
     * <br>4. Вычисляется контрольное число как остаток от деления контрольной суммы на 10
     * <br>5. Если получился ноль - то true.
     * <br>ps. Есть ещё одна проверка счета на сверку с ключом, но мы её не делаем.
     *
     * @param accountNumber номер счета
     * @param bic           БИК
     * @return если
     */
    public boolean valid(String accountNumber, String bic) {
        String bicAccNum = accountNumber.endsWith(bic.substring(6)) && accountNumber.startsWith("301")
                ? "0" + bic.substring(4, 6) + accountNumber
                : bic.substring(6) + accountNumber;

        byte[] bicAccNums = bicAccNum.getBytes();
        int[] coefficients = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};
        int checksum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            checksum += (bicAccNums[i] % 10) * coefficients[i];
        }
        return checksum % 10 == 0;
    }
}