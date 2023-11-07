package lotto.util;

import java.util.Objects;
import lotto.constants.PrintMessages;

public class ConsolePrinter {
    private static void nullCheck(Object target) {
        Objects.requireNonNull(target);
    }

    public static void showMessage(PrintMessages message) {
        nullCheck(message);

        System.out.print(message.getMessage());
    }

    public static void showMessageWithNumber(PrintMessages message, Integer number) {
        nullCheck(message);
        nullCheck(number);

        String showMessage = String.format(message.getMessage(), number);
        System.out.print(showMessage);
    }
}
