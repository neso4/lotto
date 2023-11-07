package lotto.exception;

public class InvalidPurchaseCostException {
    private final static int LOTTO_PRICE = 1000;
    private final static String ERROR = "[ERROR] ";

    public void validatePurchaseCostException(String price) {
        validIsNumber(price);
        validPrice(price);
    }

    public void validPrice(String pay) {
        int price = Integer.parseInt(pay);
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR + "구매는 1,000원 단위로 가능합니다.");
        }
    }

    public static void validIsNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new NumberFormatException(ERROR + "입력은 숫자만 가능합니다.");
            }
        }
    }
}
