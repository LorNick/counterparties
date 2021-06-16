package ru.krivko.exception;

/**
 * Исключение REST - HttpStatus.NOT_FOUND
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}