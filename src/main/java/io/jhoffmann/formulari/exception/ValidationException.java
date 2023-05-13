package io.jhoffmann.formulari.exception;

public final class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super();
    }
}
