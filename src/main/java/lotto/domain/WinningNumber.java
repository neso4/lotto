package lotto.domain;

import lotto.validator.LottoValidator;

public class WinningNumber {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumber(Lotto winningNumbers, int bonusNumber) {
        validate();
        this.lotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate() {
        LottoValidator lottoValidator = new LottoValidator(lotto.getNumbers());
        lottoValidator.validateBonusNumber(bonusNumber);
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

}
