package lotto.Model;

import static lotto.Domain.LottoResult.Rank.*;

import java.util.ArrayList;
import java.util.List;
import lotto.Domain.Lotto;
import lotto.Domain.LottoGroup;
import lotto.Domain.LottoResult.LottoResult;
import lotto.Domain.WinningLotto.WinningLotto;

public class LottoResultHandlerModel {

    private LottoResult lottoResult;
    private WinningLotto winningLotto;
    private LottoGroup lottoGroup;

    public LottoResultHandlerModel(WinningLotto winningLotto, LottoGroup lottoGroup) {
        lottoResult = new LottoResult();
        this.winningLotto = winningLotto;
        this.lottoGroup = lottoGroup;
    }

    public void checkAllWinning() {
        for (int i = 0; i < lottoGroup.findLottoNumbersSize(); i++) {
            Lotto lottoByIndex = lottoGroup.findLottoByIndex(i);
            Integer matchCount = checkWinning(winningLotto.getLotto(), lottoByIndex);
            Integer prize = convertRank(matchCount, checkBonusNumber(lottoByIndex));
            lottoResult.addPrizeCount(prize);
        }
    }

    private Integer checkWinning(Lotto winningLotto, Lotto purchasedLotto) {
        List<Integer> matchNumbers = new ArrayList<>(purchasedLotto.getNumbers());
        matchNumbers.retainAll(winningLotto.getNumbers());
        return matchNumbers.size();
    }

    private boolean checkBonusNumber(Lotto purchasedLotto) {
        return purchasedLotto.getNumbers().contains(winningLotto.getBonusNumber().getNumber());
    }

    private Integer convertRank(Integer matchCount, boolean isBonusNumberMatched) {
        if (matchCount == 6)
            return FIRST.getRank();
        if (matchCount == 5 && isBonusNumberMatched)
            return SECOND.getRank();
        if (matchCount == 5)
            return THIRD.getRank();
        if (matchCount == 4)
            return FOURTH.getRank();
        if (matchCount == 3)
            return FIFTH.getRank();

        return MISS.getRank();
    }
}