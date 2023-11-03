package lotto.Validator;

import static lotto.Validator.ValidatorConstant.*;

import java.util.List;

public class Validator {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOW_NUMBER = 1;
    private static final int HIGH_NUMBER = 45;

    public static int validateParseInt(String input) {
        try {
            int result = Integer.parseInt(input);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PARSE_INT_ERROR.getMessage());
        }
    }

    public static void validateDivisibleBy1000(int input) {
        if (input % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(Not_DIVISION_BY_1000_ERROR.getMessage());
        }
    }

    private static void valiateEqualNums(int num1, int num2) {
        if (num1 == num2) {
            throw new IllegalArgumentException();
        }
    }

    public static void valiateDuplicateNums(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                valiateEqualNums(numbers.get(i), numbers.get(j));
            }
        }
    }

    public static void validateLastComma(String input) {
        if (input.charAt(input.length() - 1) == ',') {
            throw new IllegalArgumentException();
        }
    }

    public static void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNumberRange(int number) {
        if (number < LOW_NUMBER || number > HIGH_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNumbersRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumberRange(number);
        }
    }
}
