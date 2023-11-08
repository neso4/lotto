package lotto.validator;

import static lotto.config.LottoConfig.lottoPrice;
import static lotto.message.ErrorMessage.PURCHASE_AMOUNT_IS_NOT_JUSTIFIED_MESSAGE;
import static lotto.message.ErrorMessage.PURCHASE_AMOUNT_IS_NOT_NUMERIC_MESSAGE;

public class PurchaseAmountValidator {

    public void validate(String purchaseAmount) {
        isPurchaseAmountNumeric(purchaseAmount);
        int purchaseAmountNum = Integer.parseInt(purchaseAmount);
        isPurchaseAmountJustified(purchaseAmountNum);
    }

    private void isPurchaseAmountJustified(int purchaseAmountNum) {
        if (purchaseAmountNum <= 0 || purchaseAmountNum % lottoPrice != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_IS_NOT_JUSTIFIED_MESSAGE.getMessage());
        }
    }


    private void isPurchaseAmountNumeric(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_IS_NOT_NUMERIC_MESSAGE.getMessage());
        }

    }
}
