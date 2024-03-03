package dev.feliperos.core.exceptions;

public class InvalidFieldException extends Exception {
    public InvalidFieldException() {
        super("Field cannot be null!");
    }
}
