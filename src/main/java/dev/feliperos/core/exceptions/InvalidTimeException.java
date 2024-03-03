package dev.feliperos.core.exceptions;

public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super("Time cannot be less or equal to 0.");
    }
}
