package lotto;

public final class Message {
    public static String EMPTY_INPUT_ERROR="[ERROR] 숫자 입력이 필요합니다.";
    public static String NOT_VALID_NUMBER="[ERROR] 1000 단위의 양의 정수 입력만 가능합니다.";
    public static String NOT_VALID_LENGTH = "[ERROR] 숫자는 컴마(,)를 기준으로 6개만 입력이 가능합니다.";
    public static String OUT_OF_RANGE_NUMBER = "[ERROR] 숫자는 1~45까지만 입력이 가능합니다.";
    public static String NOT_COMPOSED_OF_UNIQUE_NUMBERS = "[ERROR] 로또 번호는 중복되서는 안됩니다.";

    public static String purchaseAmountMessage = "구입 금액을 입력해 주세요.";
    public static String WinningNumbersMessage = "당첨 번호를 입력해 주세요.";
    public static String BonusNumberMessage = "보너스 번호를 입력해 주세요.";

    private Message(){}
}
