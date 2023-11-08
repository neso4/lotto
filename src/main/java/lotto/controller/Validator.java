package lotto.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class Validator {

    public static final String DELEMTER = ",";
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final int SIZE = 6;
    private static final int UNIT = 1000;

    public static boolean isBuyAmountValid(String playerInput) {
        try {
            int buyAmount = Integer.parseInt(playerInput);
            if (buyAmount < MIN_VALUE) {
                Exception.isNotPlusInt();
                return false;
            }

            if (buyAmount % UNIT != 0) {
                Exception.isNotThousandUnit();
                return false;
            }

        } catch (NumberFormatException e) {
            Exception.isNotNumber();
            return false;
        }

        return true;
    }

    public static boolean isDisticnt(List<Integer> numbers) {
        return (numbers.size() == numbers.stream().distinct().count());
    }

    public static boolean isSizeSix(List<java.lang.Integer> numbers) {
        return numbers.size() == SIZE;
    }

    public static boolean isContainComma(String playerInput) {
        return playerInput.contains(DELEMTER);
    }

    public static boolean isValidAnswerLottoNumbers(String playerInput) {

        if (!isContainComma(playerInput)) {
            Exception.isNotSixByComma();
            return false;
        }

        try {
            List<Integer> numbers = Arrays.stream(playerInput.split(DELEMTER)).map(Integer::parseInt)
                    .peek(num -> {
                        if (num < MIN_VALUE || num > MAX_VALUE) {
                            Exception.isOutOfRange();
                        }
                    })
                    .toList();

            if (!isSizeSix(numbers)) {
                Exception.isNotSixByComma();
                return false;
            }

            if(!isDisticnt(numbers)) {
                Exception.isNotDistinct();
                return false;
            }

        } catch (NumberFormatException e) {
            Exception.isNotNumber();
            return false;
        }

        return true;
    }

    public static boolean isValidBonusNumber(List<Integer> answerLottoNumber, String playerInput) {
        try {
            int bonusNumber = Integer.parseInt(playerInput);
            if (bonusNumber < MIN_VALUE || bonusNumber > MAX_VALUE) {
                Exception.isOutOfRange();
            }

            if (answerLottoNumber.contains(bonusNumber)) {
                Exception.isNotDistinct();
            }

        } catch (NumberFormatException e) {
            Exception.isNotNumber();
        }

        return true;
    }
}
