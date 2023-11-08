package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String PURCHASED_TICKETS_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS_HEADER = "당첨 통계";
    private static final String WINNING_STATISTICS_LINE = "---";
    private static final String RESULT_FORMAT = "%d개 일치 (%s원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final DecimalFormat PRIZE_FORMAT = new DecimalFormat("#,##0");

    public static void printPurchasedTickets(List<LottoTicket> tickets) {
        System.out.println(tickets.size() + PURCHASED_TICKETS_MESSAGE);
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket.getNumbers().toString());
        }
    }

    public static void printWinningStatistics(LottoResult lottoResult) {
        System.out.println();
        System.out.println(WINNING_STATISTICS_HEADER);
        System.out.println(WINNING_STATISTICS_LINE);

        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks); // 순서를 뒤집습니다.

        for (Rank rank : ranks) {
            if (rank != Rank.NONE) {
                String rankMessage = makeRankMessage(rank, lottoResult.getRankCount(rank));
                System.out.println(rankMessage);
            }
        }
    }

    private static String makeRankMessage(Rank rank, int count) {
        String bonusMatch = rank.isMatchBonus() ? ", 보너스 볼 일치" : "";
        return String.format(
                "%d개 일치%s (%s원) - %d개",
                rank.getMatchCount(),
                bonusMatch,
                PRIZE_FORMAT.format(rank.getPrizeMoney()),
                count
        );
    }

    public static void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }
}
