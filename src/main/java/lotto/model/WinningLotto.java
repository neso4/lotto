package lotto.model;

public class WinningLotto {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public WinningLotto(Lotto lotto, BonusNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getLotto() {
        return this.lotto;
    }

    public BonusNumber getBonusNumber() {
        return this.bonusNumber;
    }
}
