package lotto.utils;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    public static void validateAmountNotNumber(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] : 로또 구입 금액은 정수입니다.");
        }
    }

    public static void validateAnswerNumberIsNumber(String answerNumber) {
        List<String> answerNumbers = parseAnswerNumberToList(answerNumber);
        validateNumbersAreInteger(answerNumbers);
    }

    private static List<String> parseAnswerNumberToList(String answerNumber) {
        return Arrays.stream(answerNumber.split(","))
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private static void validateNumbersAreInteger(List<String> numbers) {
        for (String number : numbers) {
            try {
                Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] : 당첨 번호는 정수입니다.");
            }
        }
    }
}
