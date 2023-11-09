package lotto.exception;

public class LottoNumbersDuplicationException extends IllegalArgumentException {

    public static final String ERROR_MESSAGE = "[ERROR] 당첨 번호 간에 중복된 숫자가 있어선 안됩니다.";

    public LottoNumbersDuplicationException() {

        super(ERROR_MESSAGE);

    }
}
