package lotto.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lotto.util.ExceptionHandler;

public class InputValidator {
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

        if (number < 1000) {
            ExceptionHandler.throwNumberBelow1000Exception();
        }
    }
}
