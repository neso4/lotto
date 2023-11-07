package lotto.controller.dto;


import lotto.view.exception.ExceptionMessage;

public class LottoBonusNumberCreateRequest {
    private final int bonusNumber;

    public LottoBonusNumberCreateRequest(String bonusNumber) {
        try {
            this.bonusNumber = Integer.parseInt(bonusNumber);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_LOTTO_NUMBER_FORMAT);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
