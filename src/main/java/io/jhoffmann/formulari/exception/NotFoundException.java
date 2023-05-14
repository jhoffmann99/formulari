package io.jhoffmann.formulari.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super();
    }
}
