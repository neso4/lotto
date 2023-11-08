package type;

public enum ErrorType {
    INVALID_PRICE_INPUT("[ERROR] 구입 금액은 0원보다 크고 1000원 으로 나누어 떨어져야 합니다."),
    INVALID_WINNING_NUMBER_INPUT("[ERROR] 당첨 번호는 쉼표로 구분하여 6자리를 입력 하여야 합니다."),
    INVALID_NUMBER_DIGITS("[ERROR] 6자리의 숫자가 생성되어야 합니다."),
    INVALID_NUMBER_DUPLICATED("[ERROR] 중복된 숫자가 입력되면 안됩니다."),
    INVALID_BONUS_NUMBER_DUPLICATED("[ERROR] 보너스 넘버는 당첨번호와 중복된 숫자가 아니여야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("[ERROR] 보너스 넘버는 0이상 45 이하의 숫자여야 합니다."),
    INVALID_NUMBER_INPUT("[ERROR] 숫자 입력만 가능합니다.");

    private final String errorText;

    ErrorType(String errorText) {
        this.errorText = errorText;
    }

    public String getText() {
        return errorText;
    }
}
