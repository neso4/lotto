package lotto.controller;

public class ExceptionHandler {
    private static final String ERROR_MSG_HEADER = "[ERROR] ";

    public static void handleException(IllegalArgumentException e) {
        System.out.println(ERROR_MSG_HEADER + e.toString());
    }
}
