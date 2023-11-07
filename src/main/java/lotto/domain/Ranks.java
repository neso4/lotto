package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranks {

    private final List<Rank> ranks;

    public Ranks(Lottery lottery, Lotto winningNumber, LottoNumber bonusNumber) {
        this.ranks = makeRankResult(lottery, winningNumber, bonusNumber);
    }

    private List<Rank> makeRankResult(Lottery lottery, Lotto winningNumber, LottoNumber bonusNumber) {
        return lottery.calLottoRank(winningNumber, bonusNumber);
    }

    public Long calWinningPrice() {
        Long resultWinningPrice = 0L;
        for (Rank rank : ranks) {
            resultWinningPrice += rank.getWinningMoney();
        }
        return resultWinningPrice;
    }

    public Map<Rank, Integer> lotteryRankStatus() {
        Map<Rank, Integer> lottoResult = new HashMap<>();

        for (Rank rank : ranks) {
            lottoResult.put(rank, lottoResult.getOrDefault(rank, 0) + 1);
        }
        return lottoResult;
    }
}
