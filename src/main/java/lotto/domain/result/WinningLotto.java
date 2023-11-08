package lotto.domain.result;

import lotto.domain.Ranking;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

public class WinningLotto {

    private final Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public void saveBonusNumber(LottoNumber bonusNumber) {
        validateBonusNumberNotInWinningNumbers(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberNotInWinningNumbers(LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 넘버는 당첨 번호와 중복되어선 안됩니다");
        }
    }

    public Ranking getRanking(Lotto lotto) {
        int correctCount = winningLotto.getCorrectCount(lotto);
        boolean hasBonus = lotto.contains(bonusNumber);
        return Ranking.from(correctCount, hasBonus);
    }

}
