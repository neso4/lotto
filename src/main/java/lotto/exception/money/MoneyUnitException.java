package lotto.exception.money;

public class MoneyUnitException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "[ERROR] 구입 금액은 1000원 단위여야만 합니다. : %d";

    public MoneyUnitException(long money) {
        super(String.format(ERROR_MESSAGE, money));
    }
}