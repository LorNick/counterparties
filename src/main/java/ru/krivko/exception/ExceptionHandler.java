package ru.krivko.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    /**
     * Исключение - объект не найден
     *
     * @param exception NotFoundException
     * @return HttpStatus.NOT_FOUND
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CounterpartyIncorrectData> handleException(NotFoundException exception) {
        CounterpartyIncorrectData data = new CounterpartyIncorrectData();
        data.setInfo(exception.getMessage());
        log.info("**** " + exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * Исключение - все остальные
     *
     * @param exception Exception
     * @return HttpStatus.BAD_REQUEST
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CounterpartyIncorrectData> handleException(Exception exception) {
        CounterpartyIncorrectData data = new CounterpartyIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}