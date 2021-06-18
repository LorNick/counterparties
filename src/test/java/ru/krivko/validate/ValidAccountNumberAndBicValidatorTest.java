package ru.krivko.validate;

import javax.validation.ClockProvider;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.krivko.entity.Counterparty;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * Тестируем валидацию номера счета и БИК
 */
class ValidAccountNumberAndBicValidatorTest {
    private static final String WRONG_ACCOUNT_NUMBER = "30101810100000000135";
    private static final String WRONG_BIC = "042007865";

    private static final String CORRECT_NAME = "ОАО Тест";
    private static final String CORRECT_INN = "7731347089";
    private static final String CORRECT_KOP = "123456789";
    private static final String CORRECT_ACCOUNT_NUMBER = "30101810100000000835";
    private static final String CORRECT_BIC = "042007835";

    private static Counterparty counterparty;

    @BeforeEach
    void init() {
        counterparty = new Counterparty(CORRECT_NAME, CORRECT_INN, CORRECT_KOP, CORRECT_ACCOUNT_NUMBER, CORRECT_BIC);
    }

    @Test
    void testIsValidTrue() {
        var validator = new ValidAccountNumberAndBicValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(validator.isValid(counterparty,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    @Test
    void testIsValidFalse() {
        counterparty.setAccountNumber(WRONG_ACCOUNT_NUMBER);
        counterparty.setBic(WRONG_BIC);
        var validator = new ValidAccountNumberAndBicValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(validator.isValid(counterparty,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}