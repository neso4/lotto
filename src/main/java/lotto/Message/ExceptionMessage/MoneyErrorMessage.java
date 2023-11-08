package lotto.Message.ExceptionMessage;

public enum MoneyErrorMessage {
    RANGE_ERROR_MESSAGE("1000이상 100,000이하의 값만 입력할 수 있습니다. \n 입력값 : %s\n\n"),
    DIVISIBILITY_ERROR_MESSAGE("1000원으로 나누어 떨어지는 수만 입력 가능합니다. \n 입력값 : %s\n\n");

    private final String message;

    MoneyErrorMessage(String message) {
        this.message = PrefixMessage.ERROR_MESSAGE.getMessage() + message;
    }

    public String getMessage(Integer money){
        return String.format(message, money);
    }
}