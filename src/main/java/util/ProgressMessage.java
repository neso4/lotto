package util;

public enum ProgressMessage {
    INPUT_AMOUNT("구입금액을 입력해 주세요."),
    OUTPUT_BUY_NUMBER("개를 구매했습니다.");

    private final String message;

    ProgressMessage(String message) {
        this.message = message;
    }

    public String get(){
        return message;
    }
}
