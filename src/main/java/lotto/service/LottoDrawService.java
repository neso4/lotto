package lotto.service;

import lotto.constants.LottoRankConstants;
import lotto.dto.Lotto;
import lotto.dto.LottoRanks;
import lotto.dto.Lottos;
import lotto.dto.WinningLotto;

public class LottoDrawService {
    LottoRanks lottoRanks = new LottoRanks();

    public LottoRanks evaluateRanks(WinningLotto winningLotto, Lottos purchasedLottos) {
        Comparator comparator = new Comparator(winningLotto);
        for (Lotto purchasedLotto : purchasedLottos.lottos()) {
            LottoRankConstants rank = comparator.compareWithWinningLotto(purchasedLotto);
            storeRank(rank);
        }
        return lottoRanks;
    }

    public int getWinningAmount() {
        int totalAmount = 0;
        for (LottoRankConstants rank : LottoRankConstants.values()) {
            totalAmount += lottoRanks.getRankCount(rank) * rank.getPrizeAmount();
        }
        return totalAmount;
    }

    private void storeRank(LottoRankConstants lottoRank) {
        this.lottoRanks = lottoRanks.recordRank(lottoRank);
    }
}
