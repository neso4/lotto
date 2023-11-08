package lotto;

import lotto.enums.ErrorMessage;
import lotto.enums.LottoInfo;

import java.util.List;

public class Validation {
    public static void validateNotNull(List<Integer> input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.NULL_ERROR.getMessage());
        }
    }

    public static void validateNaturalNumber(String input) {
        boolean isInteger = input.trim().matches("^[0-9]+$");
        if (!isInteger || Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NATURAL_NUMBER.getMessage());
        }
    }

    public static void validateNaturalNumber(List<String> input) {
        input.forEach(Validation::validateNaturalNumber);
    }

    public static void validateMultipleOfLottoPrice(int x) {
        if (x%LottoInfo.LOTTO_PRICE.getNumber() != 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_MULTIPLE_OF_LOTTO_PRICE.getMessage());
        }
    }

    public static void validateInRange(int min, int max, int item) {
        if (!(min <= item && item <= max)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_IN_RANGE.getMessage());
        }
    }

    public static void validateInRange(int min, int max, List<Integer> lotto) {
        lotto.forEach(i -> {
            validateInRange(min, max, i);
        });
    }
}
