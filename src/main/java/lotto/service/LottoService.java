package lotto.service;

import static java.lang.String.format;
import static lotto.constant.ErrorMessage.*;
import static lotto.constant.LottoSetting.*;


public class LottoService {
    public void purchase(String purchaseAmountInput) {
        Integer purchaseAmount = validatePurchaseAmount(purchaseAmountInput);
    }

    private Integer validatePurchaseAmount(String purchaseAmountInput) {
        Integer purchaseAmount = validateNumeric(purchaseAmountInput);
        purchaseAmount = validateDivisible(purchaseAmount);
        return purchaseAmount;
    }

    private Integer validateNumeric(String purchaseAmountInput) {
        try {
            return Integer.parseInt(purchaseAmountInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_NUMERIC.getMessage());
        }
    }

    private Integer validateDivisible(Integer purchaseAmount) {
        if (purchaseAmount % DIVISOR.getValue() != 0) {
            throw new IllegalArgumentException(
                    format(NOT_DIVISIBLE.getMessage(), DIVISOR.getValue())
            );
        }

        return purchaseAmount / DIVISOR.getValue();
    }
}
