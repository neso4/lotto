package lotto.model;

import lotto.exception.Exception;

public class Money {

    private static final int MIN_MONEY_RANGE = 1000;
    private static final int MAX_MONEY_RANGE = 1000000000;
    private static final int MONEY_MAX_LENGTH = 10;

    private final int cost;
    private int money;

    public Money(String money){
        this.money = validateMoney(money);
        this.cost = this.money;
    }

    int validateMoney(String moneyInput) {
        validateEmptyInput(moneyInput);
        validateMoneyLength(moneyInput);
        validateMoneyNumeric(moneyInput);
        validateMoneyInRange(Long.parseLong(moneyInput));
        validateMoneyDividedTByThousand(Integer.parseInt(moneyInput));
        return Integer.parseInt(moneyInput);
    }

    void validateEmptyInput(String userInput) {
        if (userInput.isEmpty() || userInput.isBlank()) {
            Exception.raiseInvalidInputException();
        }
    }

    void validateMoneyLength(String money) {
        if (money.length() > MONEY_MAX_LENGTH) {
            Exception.raiseInvalidMoneyRangeException();
        }
    }

    void validateMoneyInRange(long money) {
        if (money < MIN_MONEY_RANGE || money > MAX_MONEY_RANGE) {
            Exception.raiseInvalidMoneyRangeException();
        }
    }

    void validateMoneyNumeric(String money) {
        for (int moneyIndex = 0; moneyIndex < money.length(); moneyIndex++) {
            if (!Character.isDigit(money.charAt(moneyIndex))) {
                Exception.raiseInvalidMoneyArgumentException();
            }
        }
    }

    void validateMoneyDividedTByThousand(int money) {
        if (money % LottoUtils.LOTTO_PRICE != 0) {
            Exception.raiseMoneyNotDevidedByThousandException();
        }
    }

    protected boolean canPurchaseLotto() {
        return money >= LottoUtils.LOTTO_PRICE;
    }

    protected void purchaseLotto() {
        money -= LottoUtils.LOTTO_PRICE;
    }

    public int getCost() {
        return this.cost;
    }
}
