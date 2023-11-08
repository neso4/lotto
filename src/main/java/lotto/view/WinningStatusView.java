package lotto.view;

import java.util.List;
import lotto.domain.Rank;
import lotto.domain.RankCountPair;

public class WinningStatusView {
    private static final String HEADER = "당첨 통계";
    private static final String SEPERATION_LINE = "---";
    private static final String RANK_COUNT_SEPARATOR = " - ";
    private static final String COUNT_UNIT = "개";

    public static void viewWinningStatus(List<RankCountPair> rankCountPairs) {
        rankCountPairs.forEach((rankCountPair) ->
                viewWinningStatusEachRank(rankCountPair.getRANK(), rankCountPair.getCOUNT()));
    }

    private static void viewWinningStatusEachRank(Rank rank, int count) {
        System.out.println(rank.toMessage() + RANK_COUNT_SEPARATOR + count + COUNT_UNIT);
    }
}
