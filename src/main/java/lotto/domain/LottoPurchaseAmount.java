package lotto.domain;

public class LottoPurchaseAmount {
    private static final String MINIMUM_AMOUNT_ERROR_MESSAGE = "최소 1개 이상의 로또를 구매해야 합니다.";
    private static final String AMOUNT_DIVISIBLE_ERROR_MESSAGE = "로또 구매는 1,000원 단위로만 가능합니다.";

    private final int value;

    LottoPurchaseAmount(int value) {
        validateMinimumAmount(value);

        this.value = value;
    }

    private void validateMinimumAmount(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(MINIMUM_AMOUNT_ERROR_MESSAGE);
        }
    }

    public static LottoPurchaseAmount from(int price) {
        validateDivisible(price);

        int amount = calculatePurchaseAmount(price);
        return new LottoPurchaseAmount(amount);
    }

    private static void validateDivisible(int price) {
        if (price % Lotto.PRICE != 0) {
            throw new IllegalArgumentException(AMOUNT_DIVISIBLE_ERROR_MESSAGE);
        }
    }

    private static int calculatePurchaseAmount(int price) {
        return price / Lotto.PRICE;
    }

    public int value() {
        return value;
    }
}
