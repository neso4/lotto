package lotto.domain.model.result;

import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lotto.LottoWinningNumbers;

public class WinningRankCalculator {

    //TODO 디미터 법칙 해결, 이 객체가 있어야되는지 고민해볼 것
    public WinningRank determineWinningRank(Lotto lotto, LottoWinningNumbers lottoWinningNumbers) {
        int countOfMatchingNumbers = lotto.countMatchingNumbers(lottoWinningNumbers.getWinningNumbers());
        boolean isBonusMatched = lotto.contains(lottoWinningNumbers.getBonusNumber());
        return WinningRank.from(countOfMatchingNumbers, isBonusMatched);
    }
}
