package lotto.domain;

import java.util.regex.Pattern;

import static lotto.constant.Config.PRICE_PER_LOTTO;
import static lotto.constant.ErrorMessage.*;

public class BuyPrice {
    private final int price;

    public BuyPrice(int price) {
        validateNotNegative(price);
        validateDividedBy(price);
        this.price = price;
    }

    // Creating instance method for factory pattern
    public static BuyPrice from(String price) {
        validateNumericString(price);
        return new BuyPrice(Integer.parseInt(price));
    }

    private static void validateNumericString(String price) {
        if (!Pattern.compile("-?\\d+").matcher(price).matches()) {
            throw new IllegalArgumentException(PRICE_NOT_NUMERIC);
        }
    }

    private static void validateNotNegative(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(PRICE_NEGATIVE);
        }
    }

    private static void validateDividedBy(int price) {
        if (price % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(PRICE_NOT_DIVIDED);
        }
    }

    public int getBuyCount() {
        return price / PRICE_PER_LOTTO;
    }
}
