package dev.feliperos.core.exceptions;

public class InvalidValueException extends Exception {
    public InvalidValueException() {
        super("Value cannot be null!");
    }
}
