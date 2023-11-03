package lotto.exception;

public class ParseException extends IllegalArgumentException {

    public static final String INVALID_INPUT = "입력값이 잘못되었습니다.";

    public ParseException() {
        super(INVALID_INPUT);
    }

    public ParseException(String s) {
        super(s);
    }
}
