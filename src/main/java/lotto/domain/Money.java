package lotto.domain;

import lotto.constants.LottoStatus;

public class Money {
    private final long money;
    private final long howManyLotto;

    public Money(long money) {
        isMoreThanPrice(money);
        isDividedByPrice(money);
        this.money = money;
        this.howManyLotto = money / LottoStatus.PRICE;
    }

    private void isMoreThanPrice(long money) {
        if (money < 1000) {
            throw new IllegalArgumentException();
        }
    }

    private void isDividedByPrice(long money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public long getMoney() {
        return money;
    }

    public long getHowManyLotto() {
        return howManyLotto;
    }

}
