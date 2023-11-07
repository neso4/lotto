package lotto.view;

import java.util.ArrayList;

public class InputValidator {
    public static ArrayList<Integer> validateAndParseNumbersInput(String input) {
        ArrayList<Integer> inputNumbers = new ArrayList<>();
        String[] winningNumbers = input.split(",");

        for (String winningNumber : winningNumbers) {
            inputNumbers.add(validateAndParseInput(winningNumber));
        }
        return inputNumbers;
    }

    public static int validateAndParseInput(String input) {
        validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    private static void validateIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력에 숫자가 아닌 문자가 포함되어 있거나 숫자가 너무 큽니다.");
        }
    }
}