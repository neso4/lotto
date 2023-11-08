package lotto;

import java.text.NumberFormat;

public enum LottoResult {
    NOTING(0L),
    FIFTH_PRIZE(5000L),
    FOURTH_PRIZE(50000L),
    THIRD_PRIZE(1500000L),
    SECOND_PRIZE(30000000L),
    FIRST_PRIZE(2000000000L);

    private final long price;

    LottoResult(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return String.format(LottoStringFormat.LOTTO_RESULT_TO_STRING_FORMAT, numberFormat.format(price));
    }
}
