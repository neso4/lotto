package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.constant.ExceptionMessage;
import lotto.view.OutputHandler;

public class Converter {

    public static long pay(String paymentPrice) throws IllegalArgumentException {
        try {
            return Long.parseLong(paymentPrice);
        } catch (NumberFormatException e) {
            OutputHandler.requireInteger();
            throw new IllegalArgumentException(ExceptionMessage.REQUIRE_INTEGER.getMessage());
        }
    }

    public static List<Integer> winningNumbers(String numbersInput) throws IllegalArgumentException {
        try {
            String[] numbers = numbersInput.split(",");
            List<Integer> winningNumbers = Arrays.stream(numbers)
                    .map(Integer::parseInt)
                    .toList();
            return winningNumbers;
        } catch (Exception e) {
            OutputHandler.requireInteger();
            throw new IllegalArgumentException(ExceptionMessage.REQUIRE_INTEGER.getMessage());
        }
    }

    public static Integer bonusNumbers(String numberInput) throws IllegalArgumentException {
        try {
            return Integer.parseInt(numberInput);
        } catch (Exception e) {
            OutputHandler.requireInteger();
            throw new IllegalArgumentException(ExceptionMessage.REQUIRE_INTEGER.getMessage());
        }
    }
}