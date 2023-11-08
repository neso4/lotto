package lotto.config;

import static lotto.config.LottoConfig.LOTTO_NUMBER_AMOUNT_MAX;

public enum LottoErrorMessage {
    LOTTO_AMOUNT_MAX_ERROR_MESSAGE(String.format("[ERROR] 로또 번호는 %s개를 입력해야 합니다.",
            LOTTO_NUMBER_AMOUNT_MAX.getValue())),

    LOTTO_UNIQUE_ERROR_MESSAGE("[ERROR] 로또에 중복된 번호가 존재합니다.");

    private final String message;

    LottoErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
