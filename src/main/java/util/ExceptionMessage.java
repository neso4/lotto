package util;

public enum ExceptionMessage {
    PURCHASE_AMOUNT_IS_NOT_NUMBER("금액을 입력해 주셔야 합니다."),
    PURCHASE_AMOUNT_IS_NOT_EXCEED_ZERO("0보다 큰 금액을 투입해 주셔야 합니다."),
    PURCHASE_AMOUNT_IS_NOT_THOUSAND_UNITS("입력 금액은 1,000원 단위여야 합니다."),
    LOTTO_WRONG_NUMBER_OF_VALUE("6개의 값을 쉼표로 구분해 작성해 주셔야 합니다."),
    IS_RANGE_LOTTO("1에서 45사이의 값을 입력하셔야 합니다."),
    LOTTO_DUPLICATE_VALUE("서로 다른 숫자들을 입력해 주셔야 합니다."),
    BONUS_WRONG_NUMBER_OF_VALUE("보너스 번호를 입력해야합니다."),
    IS_RAGNE_BONUS("보너스 번호는 1에서 45사이의 값을 입력하셔야 합니다."),
    NOT_IN_WINNING_NUMBERS("당첨 번호에 없는 번호를 입력해야 합니다."),
    NUMBER_FORMAT_EXCEPTION("0보다 크고 2,147,483,647 이하의 값을 입력해 주셔야 합니다.");
    private static final String ERROR_TAG = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message){
        this.message = ERROR_TAG + message;
    }

    public String getMessage(){
        return message;
    }

    public void throwException(){
        throw new IllegalArgumentException(message);
    }
}
