package lotto.util;

import java.util.function.Supplier;

public class RepeatModule {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String LINE_BREAK = "\n";

    protected <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + e.getMessage() + LINE_BREAK);
            return repeat(inputReader);
        }
    }
}