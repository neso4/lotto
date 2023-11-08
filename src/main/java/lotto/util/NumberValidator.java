package lotto.util;

import java.util.List;
import lotto.constants.ErrorMessages;
import lotto.constants.LottoValues;

public class NumberValidator {
    public static void verifyPurchaseAmount(Integer amount) {
        validateNull(amount, ErrorMessages.LLOTTO_PURCHASE_AMOUNT_NOT_NULL);
        validateNegative(amount, ErrorMessages.LOTTO_PURCHASE_AMOUNT_POSITIVE_ONLY);
        validateAmountUnit(amount, ErrorMessages.LOTTO_PURCHASE_AMOUNT_UNIT);
        validateMaxValue(amount, ErrorMessages.LOTTO_PURCHASE_AMOUNT_MAX);
    }

    private static void validateNull(Object value, ErrorMessages error) {
        if (value == null) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    private static void validateNegative(Integer value, ErrorMessages error) {
        if (value <= 0) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    private static void validateAmountUnit(Integer value, ErrorMessages error) {
        Integer amountUnit = LottoValues.AMOUNT_UNIT.getValue();
        if ((value % amountUnit) != 0) {
            throw new IllegalArgumentException(error.getMessage(List.of(amountUnit)));
        }
    }

    private static void validateMaxValue(Integer value, ErrorMessages error) {
        Integer maxValue = Integer.MAX_VALUE;
        if (value > maxValue) {
            throw new IllegalArgumentException(error.getMessage(List.of(maxValue)));
        }
    }

    public static void verifyLottoNumbers(List<Integer> numbers) {
        validateNull(numbers, ErrorMessages.LOTTO_NUMBERS_NOT_NULL);
        validateNumbersCount(numbers, ErrorMessages.LOTTO_NUMBERS_COUNT);
        validateNumbersDuplicate(numbers, ErrorMessages.LOTTO_NUMBERS_DUPLICATE);
        validateNumbersRange(numbers, ErrorMessages.LOTTO_NUMBERS_RANGE);
    }

    private static void validateNumbersCount(List<Integer> lotto, ErrorMessages error) {
        Integer lottoCount = LottoValues.NUMBERS_COUNT.getValue();
        if (lotto.size() != lottoCount) {
            throw new IllegalArgumentException(error.getMessage(List.of(lottoCount)));
        }
    }

    private static void validateNumbersDuplicate(List<Integer> lotto, ErrorMessages error) {
        Integer lottoCount = LottoValues.NUMBERS_COUNT.getValue();
        if (lotto.stream().distinct().count() != lottoCount) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    private static void validateNumbersRange(List<Integer> lotto, ErrorMessages error) {
        Integer lottoCount = LottoValues.NUMBERS_COUNT.getValue();
        Integer MIN_NUMBER = LottoValues.RANGE_MIN.getValue();
        Integer MAX_NUMBER = LottoValues.RANGE_MAX.getValue();
        if (lotto.stream()
                .filter(e -> e >= MIN_NUMBER)
                .filter(e -> e <= MAX_NUMBER)
                .count() != lottoCount
        ) {
            throw new IllegalArgumentException(
                    error.getMessage(List.of(MIN_NUMBER, MAX_NUMBER))
            );
        }
    }

    public static void verifyWinningNumbers(List<Integer> numbers) {
        validateNull(numbers, ErrorMessages.WINNING_NUMBERS_NOT_NULL);
        validateNumbersCount(numbers, ErrorMessages.WINNING_NUMBERS_COUNT);
        validateNumbersDuplicate(numbers, ErrorMessages.WINNING_NUMBERS_DUPLICATE);
        validateNumbersRange(numbers, ErrorMessages.WINNING_NUMBERS_RANGE);
    }
}
