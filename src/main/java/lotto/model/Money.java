package lotto.model;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private int value;

    Money(int value) {
        validate(value);
        this.value = value;
    }

    public int getCount() {
        return value / LOTTO_PRICE;
    }

    private void validate(int value) {
        if(value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException();
        }
    }
}
