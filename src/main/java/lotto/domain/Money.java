package lotto.domain;

public class Money {
    private static final String COMMA_EVERY_THREE_DIGITS = "%,d";
    private static final int ZERO = 0;
    private static final int LOTTO_PRICE = 1000;
    private int money;

    public Money(int money) {
        this.money = money;
    }

    public int getNumberOfPurchase() {
        return money / LOTTO_PRICE;
    }

    public void zero() {
        this.money = ZERO;
    }

    public String getMoneyPrintFormat() {
        return String.format(COMMA_EVERY_THREE_DIGITS, this.money);
    }
}