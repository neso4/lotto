package lotto.domain;

import static lotto.view.constatnt.Constant.DIVIDE_AMOUNT;

public class Money {
    private final int money;


    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money % DIVIDE_AMOUNT != 0) {
            throw new IllegalArgumentException();
        }
    }

    public int divideMoney() {
        return money / DIVIDE_AMOUNT;
    }
}
