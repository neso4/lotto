package lotto.utils.validation;

import lotto.utils.Constants;

public class Validator {
    private int TICKET_PRICE = Constants.TICKET_PRICE.getValue();

    // Features
    public int validateMoney(String string) {
        int money = validateisNumber(string);
        validateMoneyCorrectAmount(money);

        return money;
    }


    // Subclass Implements
    protected int validateisNumber(String string) {
        int money;
        try {
            money = Integer.parseInt(string);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        return money;
    }


    // Internal Implements
    private void validateMoneyCorrectAmount(int money) {
        if (isCorrectAmount(money)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isCorrectAmount(int money) {
        return money <= 0 || money % TICKET_PRICE != 0;
    }

}
