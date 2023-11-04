package lotto.model;

public enum LottoPrice {
    STANDARD_PRICE(1_000);

    private static final String INDIVISIBLE_AMOUNT_EXCEPTION_FORMAT = "로또 구입 금액은 %,d원 단위로 입력해야 합니다.";
    private static final int ZERO = 0;

    private final int price;

    LottoPrice(int price) {
        this.price = price;
    }

    public void validateDivisibility(int investMoney) {
        if (!isDivisible(investMoney)) {
            String exceptionMessage = String.format(INDIVISIBLE_AMOUNT_EXCEPTION_FORMAT, price);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private boolean isDivisible(int investMoney) {
        return investMoney % price == ZERO;
    }

    public int multiply(int count) {
        return price * count;
    }

    public int calculateLottoCount(InvestMoney investMoney) {
        return investMoney.calculateLottoPurchaseCount(price);
    }
}
