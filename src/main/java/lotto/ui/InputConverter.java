package lotto.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.constants.Constants;

public class InputConverter {
    public long convertToMoney(String input) {
        isOnlyNumber(input);
        return Long.parseLong(input);
    }

    public int convertToBonusNumber(String input) {
        isOnlyNumber(input);
        return Integer.parseInt(input);
    }

    private void isOnlyNumber(String input) {
        if (!input.matches("^[0-9]*$")) {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE);
        }
    }

    public List<Integer> convertToWinningNumbers(String input) {
        List<String> splitInput = splitByComma(input);
        hasOnlyNumbers(splitInput);
        List<Integer> numbers = new ArrayList<>();
        for (String number : splitInput) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }

    private List<String> splitByComma(String input) {
        return Arrays.asList(input.split(","));
    }

    private void hasOnlyNumbers(List<String> numbers) {
        for (String number : numbers) {
            if (!number.matches("^[0-9]*$")) {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE);
            }
        }
    }
}
