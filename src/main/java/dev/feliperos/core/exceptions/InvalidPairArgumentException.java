package dev.feliperos.core.exceptions;

public class InvalidPairArgumentException extends Exception {
    public InvalidPairArgumentException() {
        super("The size of the Keys and Values must be the same!");
    }
}
