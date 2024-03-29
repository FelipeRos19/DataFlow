package dev.feliperos.core.exceptions;

public class InvalidResultException extends Exception {
    public InvalidResultException() {
        super("Result cannot be null!");
    }
}
