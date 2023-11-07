package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;

import java.util.HashMap;
import java.util.List;

public class OutputView {
    private static final String NUMBER_OF_LOTTOS_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계\n---";
    private static final String WINNING_RATE_MESSAGE_PREFIX = "총 수익률은 ";
    private static final String WINNING_RATE_MESSAGE_SUFFIX = "%입니다.";

    public void printNumberOfLottosPurchased(int number) {
        System.out.println(number + NUMBER_OF_LOTTOS_MESSAGE);
    }

    public void printLottosPurchased(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printRankCounts(HashMap<LottoRank, Integer> rankCounter) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(getCountsMessage(rankCounter, LottoRank.FIFTH_PRIZE));
        System.out.println(getCountsMessage(rankCounter, LottoRank.FOURTH_PRIZE));
        System.out.println(getCountsMessage(rankCounter, LottoRank.THIRD_PRIZE));
        System.out.println(getCountsMessage(rankCounter, LottoRank.SECOND_PRIZE));
        System.out.println(getCountsMessage(rankCounter, LottoRank.FIRST_PRIZE));
    }

    private String getCountsMessage(HashMap<LottoRank, Integer> rankCounter, LottoRank lottoRank) {
        return lottoRank.getMessageWith(rankCounter.get(lottoRank));
    }

    public void printWinningRate(double winningRate) {
        System.out.println(WINNING_RATE_MESSAGE_PREFIX + String.format("%.1f", winningRate)
                + WINNING_RATE_MESSAGE_SUFFIX);
    }
}
