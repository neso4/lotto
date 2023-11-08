package lotto.model;

import static lotto.util.Constant.*;

import java.util.Map;

public class ProfitCalculator {

    private final double profitRatio;

    public ProfitCalculator(double purchaseMoney, Statistics statistics) {
        double totalPrize = calculateTotalPrize(statistics);
        this.profitRatio = calculateProfitRatio(purchaseMoney, totalPrize);
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    private double calculateProfitRatio(double purchaseMoney, double totalPrize) {
        return totalPrize / purchaseMoney * RATIO_NUMBER;
    }

    private double calculateTotalPrize(Statistics statistics) {
        Map<Prize, Integer> result = statistics.getResult();

        return result.entrySet()
                .stream()
                .mapToDouble(entry -> calculatePrizeForMatch(entry.getKey(), entry.getValue()))
                .sum();
    }

    private double calculatePrizeForMatch(Prize prize, int matchCount) {
        return prize.getPrize() * matchCount;
    }
}
