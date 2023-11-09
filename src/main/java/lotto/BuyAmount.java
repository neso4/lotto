package lotto;

public class BuyAmount {
    private final static Integer UNIT = 1000;
    private final static String IMPOSSIBLE_TO_CONVERT_TO_INTEGER_ERROR_MESSAGE = "[ERROR] 입력하신 값은 정수로 변환할 수 없습니다.";
    private final static String UNIT_ERROR_MESSAGE = "[ERROR] 금액이 1,000원으로 나누어 떨어지지 않습니다";
    private final static String POSITIVE_INTEGER_MESSAGE = "[ERROR] 입력하신 금액은 0 이상이어야 합니다.";
    private final Integer buyAmount;

    public BuyAmount(String buyAmount) {
        validateInteger(buyAmount);
        this.buyAmount = Integer.parseInt(buyAmount);
        validatePositveInteger(this.buyAmount);
        validateUnit(this.buyAmount);
    }

    private void validateInteger(String buyAmount) {
        try {
            Integer.parseInt(buyAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(IMPOSSIBLE_TO_CONVERT_TO_INTEGER_ERROR_MESSAGE);
        }
    }
    private void validatePositveInteger(Integer buyAmount) {
        if(buyAmount <= 0) {
            throw new IllegalArgumentException(POSITIVE_INTEGER_MESSAGE);
        }
    }
    private void validateUnit(Integer buyAmount) {
        if (buyAmount % UNIT != 0) {
            throw new IllegalArgumentException(UNIT_ERROR_MESSAGE);
        }
    }

    public Integer getBuyAmount() {
        return this.buyAmount;
    }
    public Integer getBuyCount() {
        return this.buyAmount / UNIT;
    }
}
