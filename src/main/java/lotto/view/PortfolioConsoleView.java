package lotto.view;

import static lotto.core.DrawResult.FIFTH_PRIZE;
import static lotto.core.DrawResult.FIRST_PRIZE;
import static lotto.core.DrawResult.FOURTH_PRIZE;
import static lotto.core.DrawResult.SECOND_PRIZE;
import static lotto.core.DrawResult.THIRD_PRIZE;

import lotto.core.LotteryPortfolio;
import lotto.core.LotteryTicket;
import lotto.core.PortfolioReport;

public class PortfolioConsoleView {

    private StringBuilder sb;

    public static PortfolioConsoleView reportView(PortfolioReport report) {
        return new PortfolioConsoleView(report);
    }

    public static PortfolioConsoleView holdingsView(LotteryPortfolio portfolio) {
        return new PortfolioConsoleView(portfolio);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    private PortfolioConsoleView(PortfolioReport report) {
        sb = new StringBuilder();
        sb.append(String.format("%d개 일치 (%,d원) - %d개\n",
                FIFTH_PRIZE.getHitCount(), FIFTH_PRIZE.getWinnings(), report.getFifthPlaceCount()));
        sb.append(String.format("%d개 일치 (%,d원) - %d개\n",
                FOURTH_PRIZE.getHitCount(), FOURTH_PRIZE.getWinnings(), report.getFourthPlaceCount()));
        sb.append(String.format("%d개 일치 (%,d원) - %d개\n",
                THIRD_PRIZE.getHitCount(), THIRD_PRIZE.getWinnings(), report.getThirdPlaceCount()));
        sb.append(String.format("%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n",
                SECOND_PRIZE.getHitCount(), SECOND_PRIZE.getWinnings(), report.getSecondPlaceCount()));
        sb.append(String.format("%d개 일치 (%,d원) - %d개\n",
                FIRST_PRIZE.getHitCount(), FIRST_PRIZE.getWinnings(), report.getFirstPlaceCount()));
        sb.append(String.format("총 수익률은 %.1f%%입니다.", report.getProfitRate()));
    }

    private PortfolioConsoleView(LotteryPortfolio portfolio) {
        sb = new StringBuilder();
        for (LotteryTicket lotteryTicket : portfolio.getHoldings()) {
            sb.append(lotteryTicket + "\n");
        }
    }
}
