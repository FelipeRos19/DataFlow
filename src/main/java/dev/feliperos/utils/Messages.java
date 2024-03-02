package dev.feliperos.utils;

public class Messages {
    public static String getErrorMessage(Class<?> command) {
        return command.getName() + " query error: ";
    }

    public static String getExecutedMessage(Class<?> command) {
        return command.getName() + " query has executed!";
    }
}
