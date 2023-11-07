package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_COUNT = 0;
    private static final int INCREMENT = 1;
    private static final int PERCENTAGE = 100;

    private final List<Lotto> lottos;

    public LottoResult(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> checkResult(WinningLotto winningLotto) {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : lottos) {
            Rank rank = Rank.getRankByLotto(lotto, winningLotto);
            updateRankCount(result, rank);
        }
        return result;
    }

    private void updateRankCount(Map<Rank, Integer> result, Rank rank) {
        result.put(rank, result.getOrDefault(rank, DEFAULT_COUNT) + INCREMENT);
    }

    public double calculateReturnRatePercentage(Map<Rank, Integer> result, int money) {
        double totalReward = result.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();
        double returnRatePercentage = totalReward / money * PERCENTAGE;
        return roundToTwoDecimalPlaces(returnRatePercentage);
    }

    private double roundToTwoDecimalPlaces(double returnRate) {
        return Double.parseDouble(String.format("%.1f", returnRate));
    }
}
