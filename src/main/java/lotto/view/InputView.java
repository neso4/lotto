package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.TypeValidator;
import lotto.message.ExceptionMessage;

public class InputView {
    public static String readString(String message) {
        System.out.println(message);
        String inputValue = Console.readLine();
        validateBlank(inputValue);
        return inputValue;
    }

    public static int readInteger(String message) {
        System.out.println(message);
        String inputValue = Console.readLine();
        validateInteger(inputValue);
        return Integer.parseInt(inputValue);
    }

    private static void validateBlank(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_NULL);
        }
    }

    private static void validateInteger(String inputValue) {
        validateBlank(inputValue);
        TypeValidator.validateNumber(inputValue);
    }
}
