package lotto.domain;

import java.util.List;

public class ProfitCalculator {

    private final List<Integer> winningLottoCounts;

    public ProfitCalculator(List<Integer> winningLottoCounts) {
        this.winningLottoCounts = winningLottoCounts;
    }

    public double calculateProfit(int money) {
        double sum = 0;
        for (Rank rank : Rank.values()) {
            sum += winningLottoCounts.get(rank.getRankIndex()) * rank.getPrize();
        }
        return sum / money * Constants.PERCENTAGE;
    }
}
