package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.constant.LottoPrize;
import lotto.model.Lotto;
import lotto.model.WinningStatistics;

public class OutputView {
    public static final String LOTTO_TICKETS_COUNT_FORMAT = "%d개를 구매했습니다.";
    public static final String WINNING_STATISTICS_TEMPLATE = "";

    public static String lottoTicketsCountTemplate(int count) {
        return String.format(LOTTO_TICKETS_COUNT_FORMAT, count);
    }

    public static String lottoTicketsTemplate(List<Lotto> lottoTickets) {
        StringBuilder sb = new StringBuilder();
        lottoTickets.forEach(lotto -> {
            sb.append(lotto.toString());
            sb.append("\n");
        });
        return sb.toString();
    }

    public static String winningStatisticsTemplate(WinningStatistics winningStatistics) {
        StringBuilder sb = new StringBuilder();
        Map<LottoPrize, Integer> prizeCount = winningStatistics.getPrizeCount();

        sb.append("당첨 통계\n---\n");
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            if (lottoPrize == LottoPrize.NOTHING) {
                continue;
            }

            int count = 0;
            if (prizeCount.get(lottoPrize) != null) {
                count = prizeCount.get(lottoPrize);
            }
            sb.append(String.format(lottoPrize.getStatisticsFormat(), count))
                    .append("\n");
        }
        return sb.toString();
    }
}
