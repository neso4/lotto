package lotto;

import static lotto.LottoConstants.LOTTO_TICKET_PRICE;
import static lotto.LottoConstants.ZERO;

import exception.LottoException;

public class LottoPurchaseAmount {

    private int purchaseAmount;

    public LottoPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = validate(purchaseAmount);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    private int validate(String purchaseAmount) {
        int amount = validateInt(purchaseAmount);
        validateAmount(amount);
        return amount;
    }

    private void validateAmount(int price) {
        if (price <= ZERO) {
            throw new IllegalArgumentException(LottoException.NOT_POSITIVE_NUMBER);
        }
        if ((price % LOTTO_TICKET_PRICE) != ZERO) {
            throw new IllegalArgumentException(LottoException.NOT_DIVIDE_LOTTO_TICKET_PRICE);
        }
    }

    public static int validateInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(LottoException.NOT_NUMBER);
        }
    }
}
