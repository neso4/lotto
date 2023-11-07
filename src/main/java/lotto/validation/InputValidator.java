package lotto.validation;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import lotto.domain.ConstantValue;
import lotto.util.ExceptionHandler;

public class InputValidator {

    public static void validateUserInput(String input) {
        validateForNonNumericCharacters(input);
        validateNumberLessThan1000(input);
        validateChangeAvailableFor1000(input);
    }

    public static void validateForNonNumericCharacters(String input) {
        String regex = "^[0-9]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            ExceptionHandler.throwNonNumericInputException();
        }
    }

    public static void validateNumberLessThan1000(String input) {
        int number = Integer.parseInt(input);

        if (number < ConstantValue.THOUSAND) {
            ExceptionHandler.throwNumberBelow1000Exception();
        }
    }

    public static void validateChangeAvailableFor1000(String input) {
        int number = Integer.parseInt(input);

        int remainder = number % ConstantValue.THOUSAND;
        if (remainder > 0) {
            ExceptionHandler.throwChangeAvailableException();
        }
    }

    public static void validateWinningNumber(List<Integer> winningNumbers) {
        hasDuplicateWinningNumbers(winningNumbers);
        hasValidWinningNumberCount(winningNumbers);
    }

    public static void hasDuplicateWinningNumbers(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            ExceptionHandler.throwDuplicatedWinningNumberException();
        }
    }

    public static void hasValidWinningNumberCount(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            ExceptionHandler.throwWinningNumberLengthException();
        }
    }

    public static void validateExtraWinningNumber(String extraWinningNumber, List<Integer> winningNumbers) {
        int extraWinningNumberInteger = Integer.parseInt(extraWinningNumber);

        hasDuplicateBonusNumber(extraWinningNumberInteger,winningNumbers);
    }

    public static void hasDuplicateBonusNumber(int extraWinningNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(extraWinningNumber)) {
            ExceptionHandler.throwDuplicatedExtraExtraWinningNumberException();
        }
    }

}
