package lotto.domain;

import static lotto.constant.LottoConstant.LOTTO_PRICE;

import lotto.validator.InputValidator;

public class PurchasePrice {

    private final int price;

    private PurchasePrice(String input) {
        validate(input);
        price = Integer.parseInt(input);
    }

    public static PurchasePrice from(String input) {
        return new PurchasePrice(input);
    }

    private void validate(String input) {
        InputValidator.onlyNumber(input);
        InputValidator.inRange(input);
        InputValidator.divisibleUnit(Integer.parseInt(input));
    }

    public int getPrice() {
        return price;
    }

    public int calculatePurchaseCount() {
        return price / LOTTO_PRICE;
    }
}
