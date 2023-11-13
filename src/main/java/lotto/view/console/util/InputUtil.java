package lotto.view.console.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lotto.exception.AppException;
import lotto.exception.ErrorMessage;
import lotto.exception.InvalidInputException;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_A_NUMBER);
        }
    }

    public static List<Integer> parseInputToIntegers(String input, String regex) {
        // -1을 넣을 경우 빈 문자열도 포함해서 split한다. ex) "1,2,," -> ["1", "2", "", ""]
        String[] parts = input.split(regex, -1);
        return Arrays.stream(parts)
                .map(String::trim)
                .map(InputUtil::parseInputToInt)
                .toList();
    }

    public static <T> T retryOnException(Supplier<T> supplier, boolean lineBreak) {
        while (true) {
            try {
                return supplier.get();
            } catch (AppException e) {
                System.out.println(e.getMessage());
                if (lineBreak) {
                    System.out.println();
                }
            }
        }
    }

    public static <T> T retryOnException(Supplier<T> supplier) {
        return retryOnException(supplier, false);
    }
}
