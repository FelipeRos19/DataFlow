package dev.feliperos.core.exceptions;

public class InvalidTypeException extends Exception {
    public InvalidTypeException() {
        super("Type not supported!");
    }
}
