package lotto.view;

import java.util.List;
import lotto.domain.Rank;
import lotto.domain.RankCountPair;
import lotto.domain.Reward;

public class WinningStatusView {
    private static final String STATUS_HEADER = "당첨 통계";
    private static final String SEPERATION_LINE = "---";
    private static final String RANK_COUNT_SEPARATOR = " - ";
    private static final String COUNT_UNIT = "개";
    private static final String PROFIT_RATE_HEADER = "총 수익률은 ";
    private static final String PROFIT_RATE_FOOTER = "%입니다.";

    public static void viewWinningStatus(List<RankCountPair> rankCountPairs, double profitRate) {
        System.out.println(STATUS_HEADER);
        System.out.println(SEPERATION_LINE);
        rankCountPairs.forEach((rankCountPair) ->
                viewWinningStatusEachRank(rankCountPair.getRANK(), rankCountPair.getCOUNT()));
        System.out.println(PROFIT_RATE_HEADER + profitRate + PROFIT_RATE_FOOTER);
    }

    private static void viewWinningStatusEachRank(Rank rank, int count) {
        System.out.println(rank.toMessage() + RANK_COUNT_SEPARATOR + count + COUNT_UNIT);
    }
}
