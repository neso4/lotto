package lotto.validator;

import lotto.constant.ErrorMessage;
import lotto.model.Lotto;

import java.util.Arrays;
import java.util.List;

import static lotto.constant.LottoConstant.*;

public class Validation {

    public static void checkEnter(String money) {
        if (money.length() == 0) {
            throw new IllegalArgumentException(ErrorMessage.NON_EXIST_INPUT.getMessage());
        }
    }

    public static void checkDigit(String money) {
        for (char ch : money.toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new IllegalArgumentException(ErrorMessage.NOT_DIGIT.getMessage());
            }
        }
    }

    public static void checkZero(String money) {
        if (Integer.parseInt(money) == 0) {
            throw new IllegalArgumentException(ErrorMessage.EXIST_ZERO.getMessage());
        }
    }

    public static void checkAvailableDivide(String money) {
        if (Integer.parseInt(money) % LOTTO_MONEY_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    public static void checkComma(String numbers) {
        if (!numbers.contains(",")) {
            throw new IllegalArgumentException(ErrorMessage.NON_EXIST_COMMA.getMessage());
        }
    }

    public static void checkNonSeparatedComma(String readNumbers) {
        List<String> numbers = Arrays.asList(readNumbers.split(","));
        for (String number : numbers) {
            checkEnter(number);
        }
    }

    public static List<String> checkDigitSeparatedComma(String readNumbers) {
        List<String> numbers = Arrays.asList(readNumbers.split(","));
        for (String number : numbers) {
            checkDigit(number);
        }
        return numbers;
    }

    public static void checkNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_SIZE.getMessage());
        }
    }

    public static void checkInRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < LOTTO_RANGE_START || number > LOTTO_RANGE_END) {
                throw new IllegalArgumentException(ErrorMessage.NOT_IN_RANGE.getMessage());
            }
        }
    }

    public static void checkDuplication(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.EXIST_DUPLICATE.getMessage());
        }
    }

    public static void checkInRange(String number) {
        int num = Integer.parseInt(number);
        if (num < LOTTO_RANGE_START || num > LOTTO_RANGE_END) {
            throw new IllegalArgumentException(ErrorMessage.NOT_IN_RANGE.getMessage());
        }
    }

    public static void checkExistBonus(int number, Lotto lotto) {
        if (lotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException(ErrorMessage.EXIST_BONUS.getMessage());
        }
    }

}