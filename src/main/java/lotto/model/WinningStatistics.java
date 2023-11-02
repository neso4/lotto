package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.constant.LottoConstants;
import lotto.constant.LottoPrize;

public class WinningStatistics {
    private final WinningLotto winningLotto;
    private final List<Lotto> lottoTicketsPurchased;

    private final Map<LottoPrize, Integer> prizeCount;

    public WinningStatistics(WinningLotto winningLotto, List<Lotto> lottoTicketsPurchased) {
        this.winningLotto = winningLotto;
        this.lottoTicketsPurchased = lottoTicketsPurchased;
        prizeCount = calculateWinningStatistics();
    }

    public Map<LottoPrize, Integer> getPrizeCount() {
        return prizeCount;
    }

    public Map<LottoPrize, Integer> calculateWinningStatistics() {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();
        for (Lotto lotto : lottoTicketsPurchased) {
            int matchedCount = lotto.getMatchedCount(winningLotto.getLotto());
            if (matchedCount < LottoConstants.THE_MINIMUM_NUMBER_OF_MATCHES_TO_WIN_A_PRIZE) {
                continue;
            }

            boolean bonusMatched = lotto.contains(winningLotto.getBonusNumber());
            LottoPrize lottoPrize = LottoPrize.valueOf(matchedCount, bonusMatched);
            if (prizeCount.containsKey(lottoPrize)) {
                prizeCount.put(lottoPrize, prizeCount.get(lottoPrize) + 1);
            } else {
                prizeCount.put(lottoPrize, 1);
            }
        }
        return prizeCount;
    }

    public Long calculateSumOfPrize() {
        long sum = 0L;
        for (LottoPrize lottoPrize : prizeCount.keySet()) {
            Long count = Long.valueOf(prizeCount.get(lottoPrize));
            Long prize = lottoPrize.getPrize();
            sum += prize * count;
        }
        return sum;
    }
}
