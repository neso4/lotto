package Util;

public class InputValidator {
    static final int LOTTO_PRICE = 1000;

    private int convertToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값이 입력되었습니다.");
        }
    }

    public int checkInputPurchaseAmount(String userInput) {
        int value = convertToInt(userInput);
        validatePositiveValue(value);
        validateDividedByThousand(value);
        return value;
    }

    private void validatePositiveValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수 값이 입력되야 합니다.");
        }
    }

    private void validateDividedByThousand(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원으로 나누어 떨어지지 않는 값이 입력되었습니다.");
        }
    }
}
