package lotto.constant;

public enum ExceptionConstant {

    ExceptionConstant() {
    };

    public static final String BUYING_PRICE_INPUT_EXCEPTION = "[ERROR] 구입 금액을 잘못 입력하셨습니다.";
    public static final String BUYING_PRICE_NUMBER_FORMAT_EXCEPTION = "[ERROR] 구입 금액을 숫자 형식으로 입력해주세요.";
    public static final String BONUS_NUMBER_RANGE_EXCEPTION = "[ERROR] 보너스 번호는 1이상 45이하의 숫자만 가능합니다.";
    public static final String BONUS_NUMBER_DUPLICATION_EXCEPTION = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    public static final String WINNING_NUMBER_SIZE_EXCEPTION = "[ERROR] 당첨 번호를 6자리로 입력해주세요.";
    public static final String WINNING_NUMBER_DUPLICATION_EXCEPTION = "[ERROR] 당첨 번호는 중복될 수 없습니다.";
    public static final String WINNING_NUMBER_RANGE_EXCEPTION = "[ERROR] 당첨 번호는 1이상 45이하의 숫자만 가능합니다.";
}
