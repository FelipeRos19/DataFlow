package dev.feliperos.core.exceptions;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        super("Key cannot be null!");
    }
}
