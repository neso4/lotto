package lotto.Model;

import lotto.View.ExceptionMessage;
import lotto.View.InputView;

public class UserLottoAmount {
    private static final int LOTTO_PRICE = 1000;
    private int amount;

    public UserLottoAmount(String amount) {
        int amountNum;
        while (true) {
            try {
                amountNum = ValidateNumber(amount);
                ValidateAmount(amountNum);
                this.amount = amountNum;
                break;
            } catch (IllegalArgumentException e) {
                amount = InputView.InputBuyCost();
            }
        }
    }

    public int CalcLottoCount() {
        return amount / LOTTO_PRICE;
    }

    private void ValidateAmount(int amount) {
        validateNatural(amount);
        validateDivisible(amount);
    }

    private static int ValidateNumber(String amount) throws IllegalArgumentException {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            ExceptionMessage.numberException();
            throw new IllegalArgumentException();
        }
    }

    private void validateNatural(int amount) {
        if (amount <= 0) {
            ExceptionMessage.naturalException();
            throw new IllegalArgumentException();
        }
    }

    private void validateDivisible(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            ExceptionMessage.divisibleException();
            throw new IllegalArgumentException();
        }
    }
}
