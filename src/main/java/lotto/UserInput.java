package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UserInput {

    private static final int price = 1000;

    public static int getUserMoney() {
        String input = readLine();
        validateUserMoney(input);

        return Integer.parseInt(input);
    }

    public static List<Integer> getWinningNumbers() {
        String input = readLine().replace(" ", "");
        List<String> inputs = Arrays.asList(input.split(","));
        validateWinningNumbers(inputs);

        return inputs.stream().map(Integer::parseInt).toList();
    }

    public static int getBonusNumber() {
        String input = readLine();
        validateBonusNumber(input);

        return Integer.parseInt(input);
    }

    private static void validateUserMoney(String input) {
        validateNotNumber(input);
        validateMoneyRange(Integer.parseInt(input));
    }

    private static void validateWinningNumbers(List<String> inputs) {
        for (String input : inputs) {
            validateNotNumber(input);
        }

        validateWinningNumbersNotSixDigits(inputs);

        List<Integer> winningNumbers = inputs.stream().map(Integer::parseInt)
                .filter(integer -> integer >= 1 && integer <= 45).toList();

        validateWinningNumbersRange(winningNumbers);
        validateWinningNumbersNotSame(winningNumbers);
    }

    private static void validateBonusNumber(String input) {
        if (Arrays.asList(input.split(",")).size() != 1) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_BONUS_NOT_ONE_DIGIT.getMessage());
        }

        validateNotNumber(input);
        validateBonusNumberRange(Integer.parseInt(input));
    }

    private static void validateNotNumber(String input) {
        if (!isNumberInteger(input)) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_NOT_NUMBER.getMessage());
        }
    }

    private static void validateMoneyRange(int input) {
        if (!isNumberOverThanPrice(input)) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_MONEY_UNDER_1K.getMessage());
        }
        if (!isNumberMultipleOfPrice(input)) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_MONEY_NOT_MULTIPLES.getMessage());
        }
    }

    private static void validateWinningNumbersNotSixDigits(List<String> inputs) {
        if (inputs.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_WINNIG_NUMBER_NOT_SIX_DIGITS.getMessage());
        }
    }

    private static void validateWinningNumbersRange(List<Integer> inputs) {
        if (inputs.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_WINNIG_NUMBER_OVER_RANGE.getMessage());
        }
    }

    private static void validateWinningNumbersNotSame(List<Integer> inputs) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_WINNIG_NUMBER_SAME_NUMBER.getMessage());
        }
    }

    private static void validateBonusNumberRange(int input) {
        if (!isNumberInCorrectRange(input)) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_OF_BONUS_NUMBER_OVER_RANGE.getMessage());
        }
    }

    private static boolean isNumberInteger(String input) {
        String regularExpression = "^[0-9]+$";
        return input.matches(regularExpression);
    }

    private static boolean isNumberOverThanPrice(int input) {
        return input >= price;
    }

    private static boolean isNumberMultipleOfPrice(int input) {
        return input % price == 0;
    }

    private static boolean isNumberInCorrectRange(int input) {
        return (input > 0 && input < 46);
    }

}
