package dev.feliperos.core.exceptions;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException() {
        super("Argument cannot be null!");
    }
}
