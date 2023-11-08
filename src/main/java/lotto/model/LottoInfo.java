package lotto.model;

public class LottoInfo {
    private final Lotto lotto;
    private Integer matchCount;
    private boolean matchBonus;
    public LottoInfo(Lotto lotto) {
        this.lotto = lotto;
        this.matchCount = 0;
        this.matchBonus = false;
    }
    public Lotto getLotto() {
        return this.lotto;
    }

    public Integer getMatchCount() {
        return this.matchCount;
    }

    public boolean isMatchBonus(BonusNumber bonusNumber) {
        this.matchBonus = this.contain(bonusNumber.bonusNumber());
        return this.matchBonus;
    }

    private boolean contain(Integer number) {
        return this.lotto.getNumbers().contains(number);
    }

    public Integer countMatch(Lotto winningLotto) {
        winningLotto.getNumbers().stream().filter(this::contain).forEach(number -> this.matchCount++);
        return this.matchCount;
    }

    public void resetMatchCount(){
        this.matchCount = 0;
    }
}
