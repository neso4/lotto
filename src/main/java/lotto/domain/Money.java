package lotto.domain;

public class Money {

    int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    public int get() {
        return money/1000;
    }

    private void validate(int money) {
        if(0 != money%1000)
            throw new IllegalArgumentException();
    }


}
