package lotto;

import consts.Regexp;

import java.util.List;

import static consts.ErrorMessage.*;
import static consts.Rule.*;

public class LottoValidator {
    public static void validatePurchaseAmount(final String purchaseAmount) {
        validateInteger(purchaseAmount);
        validatePurchaseAmountUnit(Integer.parseInt(purchaseAmount));
    }

    public static void validateWinningNumbers(final List<String> winningNumbers) {
        winningNumbers.forEach(LottoValidator::validateInteger);
        winningNumbers.forEach(number -> validateLottoRange(Integer.parseInt(number)));
    }

    public static void validateBonusNumber(final String bonusNumber) {
        validateInteger(bonusNumber);
        validateLottoRange(Integer.parseInt(bonusNumber));
    }

    public static void validateDuplicateNumber(final List<Integer> numbers, final int target) {
        boolean condition = !numbers.contains(target);
        throwException(condition, DUPLICATED_BONUS_NUMBER);
        LottoValidator.validateDuplicateNumber(numbers);
    }

    public static void validateDuplicateNumber(final List<Integer> numbers) {
        boolean condition = numbers.stream().distinct().count() == numbers.size();
        throwException(condition, DUPLICATED_LOTTO_NUMBER);
    }

    protected static void validateInteger(final String purchaseAmount) {
        boolean condition1 = purchaseAmount.matches(Regexp.ONLY_NUMBER);
        throwException(condition1, INVALID_NUMBER);

        boolean condition2 = Integer.parseInt(purchaseAmount) > 0;
        throwException(condition2, INVALID_NUMBER_OVER_ONE);
    }

    protected static void validatePurchaseAmountUnit(final int purchaseAmount) {
        boolean condition = purchaseAmount % LOTTO_PRICE == 0;
        throwException(condition, INVALID_PURCHASE_AMOUNT_UNIT);
    }

    protected static void validateLottoNumberCount(final int lottoNumberCount) {
        boolean condition = lottoNumberCount == LOTTO_NUMBER_COUNT;
        throwException(condition, INVALID_LOTTO_NUMBER_COUNT);
    }

    public static void validateLottoRange(final int number) {
        boolean condition = LOTTO_MIN_NUMBER <= number && number <= LOTTO_MAX_NUMBER;
        throwException(condition, INVALID_LOTTO_NUMBER_RANGE);
    }

    private static void throwException(boolean isSuccess, String errorMessage) {
        if (!isSuccess) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
