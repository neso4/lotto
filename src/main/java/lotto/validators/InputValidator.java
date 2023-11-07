package lotto.validators;

import static lotto.constant.LottoConfig.MAX_LOTTO_NUMBER;
import static lotto.constant.LottoConfig.MIN_LOTTO_NUMBER;
import static lotto.constant.LottoConfig.PURCHASE_PRICE;
import static lotto.constant.LottoConfig.TOTAL_CHOICE_NUMBER;
import static lotto.controller.LottoController.SEPARATOR;

import java.util.Arrays;
import java.util.List;
import lotto.constant.ErrorMessages;

public class InputValidator {
    private static final String VALID_NUMERIC_PATTERN = "^\\d+$";

    public static void validatePriceInput(String priceInput) {
        if (!isNumericString(priceInput)) {
            throw new IllegalArgumentException(ErrorMessages.INPUT_NUMBER);
        } else if (!isValidPurchaseUnit(priceInput)) {
            throw new IllegalArgumentException(java.lang.String.format(ErrorMessages.PRICE_UNIT, PURCHASE_PRICE.getValue()));
        }
    }

    public static void validateWinnigNumberInput(String winnigNumberInput) {
        List<String> winningNumbers = Arrays.stream(winnigNumberInput.split(SEPARATOR)).toList();

        if (!isValidWinningNumberLength(winningNumbers)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.LOTTO_CHOICE_NUMBER, TOTAL_CHOICE_NUMBER.getValue()));
        } else if (!isValidLottoNumbersRange(winningNumbers)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.LOTTO_NUMBER_RANGE, MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue()));
        } else if (hasDuplicatesWinningNumber(winningNumbers)){
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_NUMBER);
        }
    }

    public static void validateBonusNumberInput(String bonusNumberInput, List<String> winningNumbers) {
        if (!isValidLottoNumberRange(bonusNumberInput)) {
            throw new IllegalArgumentException(String.format(ErrorMessages.LOTTO_NUMBER_RANGE, MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue()));
        } else if(hasDuplicatesBonusNumber(bonusNumberInput, winningNumbers)) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATE_NUMBER);
        }
    }

    private static boolean isNumericString(String input) {
        return input.matches(VALID_NUMERIC_PATTERN);
    }

    private static boolean isValidPurchaseUnit(String input) {
        int value = Integer.parseInt(input);
        return (value % PURCHASE_PRICE.getValue()) == 0;
    }

    private static boolean isValidWinningNumberLength(List<String> winningNumbers) {
        return winningNumbers.size() == TOTAL_CHOICE_NUMBER.getValue();
    }

    private static boolean isValidLottoNumbersRange(List<String> winningNumbers) {
        return winningNumbers.stream()
                .allMatch(InputValidator::isValidLottoNumberRange);
    }

    private static boolean hasDuplicatesWinningNumber(List<String> winningNumbers) {
        long duplicateCount = winningNumbers.stream()
                .distinct()
                .count();

        return duplicateCount != winningNumbers.size();
    }

    private static boolean isValidLottoNumberRange(String input) {
        if (!isNumericString(input)) {
            return false;
        }
        int choiceNumber = Integer.parseInt(input);

        return (MIN_LOTTO_NUMBER.getValue() <= choiceNumber) && (choiceNumber <= MAX_LOTTO_NUMBER.getValue());
    }

    private static boolean hasDuplicatesBonusNumber(String bonusNumberInput, List<String> winningNumbers) {
        return winningNumbers.contains(bonusNumberInput);
    }
}
