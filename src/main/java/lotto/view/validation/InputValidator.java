package lotto.view.validation;

import lotto.message.ErrorMessage;

public class InputValidator {

    public static void validateBlank(String input) {
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANk.getValue());
        }
    }

    public static void validatePurchaseNumber(String purchaseInputNumber) {
        try {
            Integer.parseInt(purchaseInputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_IS_NOT_NUMBER.getValue());
        }
    }

    public static void validatePurchase(String purchaseInputNumber) {
        validateBlank(purchaseInputNumber);
        validatePurchaseNumber(purchaseInputNumber);
    }

    public static int validateLottoNumberAndConvertToNumeric(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_IS_NOT_NUMBER.getValue());
        }
    }

    public static void validateBonusNumber(String bonusNumber) {
        validateBlank(bonusNumber);
        try {
            Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_IS_NOT_NUMBER.getValue());
        }
    }
}
