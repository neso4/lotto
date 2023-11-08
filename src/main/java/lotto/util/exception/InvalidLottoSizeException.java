package lotto.util.exception;

import static lotto.domain.enums.LottoConstants.LOTTO_SIZE;

public class InvalidLottoSizeException extends IllegalArgumentException {
    private static final String message = "[ERROR] 로또의 숫자가 " + LOTTO_SIZE + "개 여야합니다.";

    public InvalidLottoSizeException() {
        super(message);
    }
}
