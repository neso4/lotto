package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    // TODO : enum 으로 변경해서 중복 제거.
    // TODO : equals, hashcode 구현.
    private static final int MAX_MATCH_COUNT = 6;
    private Map<Integer, Integer> matchCounts = new HashMap<>();
    private Map<Integer, Long> prizeMap = initializePrizeMap();
    private Map<Integer, Integer> secondPrizeWinners = new HashMap<>();

    public LottoResult() {
        for (int i = 0; i <= MAX_MATCH_COUNT; i++) {
            matchCounts.put(i, 0);
            secondPrizeWinners.put(5, 0);
        }
    }

    public Map<Integer, Integer> getWinningResult() {
        Map<Integer, Integer> winningResult = new HashMap<>();
        for (int i = 3; i <= MAX_MATCH_COUNT; i++) {
            winningResult.put(i, matchCounts.getOrDefault(i, 0));
        }
        int secondPrizeWinners = matchCounts.getOrDefault(5, 0);
        winningResult.put(5, secondPrizeWinners); // 5개 맞고 보너스 볼 안 맞은 경우 3등으로 처리.
        winningResult.put(2, matchCounts.getOrDefault(MAX_MATCH_COUNT, 0)); // 5개 맞고 보너스 볼 맞은 경우 2등으로 처리.

        return winningResult;
    }

    private Map<Integer, Long> initializePrizeMap() {
        Map<Integer, Long> prizeMap = new HashMap<>();
        prizeMap.put(3, 5_000L);
        prizeMap.put(4, 50_000L);
        prizeMap.put(5, 1_500_000L);
        prizeMap.put(6, 2_000_000_000L);
        return prizeMap;
    }

    public void updateMatchCount(Lotto purchasedLotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = purchasedLotto.matchCount(winningLotto);
        if (matchCount == 5 && purchasedLotto.containsNumber(bonusNumber)) {
            secondPrizeWinners.put(5, secondPrizeWinners.getOrDefault(5, 0) + 1);
        } else {
            incrementMatchCount(matchCount);
        }
    }

    private void incrementMatchCount(int matchCount) {
        matchCounts.put(matchCount, matchCounts.getOrDefault(matchCount, 0) + 1);
    }

    public void calculateResults(List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber) {
        for (Lotto purchasedLotto : purchasedLottos) {
            updateMatchCount(purchasedLotto, winningLotto, bonusNumber);
        }
    }

    public void printStatistics() {
        matchCounts.forEach((matchCount, count) -> {
            if (prizeMap.containsKey(matchCount) && count > 0) {
                printMatchStatistics(matchCount, count);
            }
        });
        int secondPrizeCount = secondPrizeWinners.getOrDefault(5, 0);
        if (secondPrizeCount > 0) {
            System.out.printf("5개 일치, 보너스 볼 일치 (%s원) - %d개\n", formatPrizeMoney(30_000_000L), secondPrizeCount);
        }
    }

    private void printMatchStatistics(int matchCount, int count) {
        System.out.printf("%d개 일치 (%s원) - %d개\n", matchCount, formatPrizeMoney(prizeMap.get(matchCount)), count);
    }

    private String formatPrizeMoney(long prizeMoney) {
        return String.format("%,d", prizeMoney);
    }

    public double calculateYield(int pricePerLotto, int totalLottoCount) {
        long totalPrize = calculateTotalPrize();
        long totalSpent = (long) pricePerLotto * totalLottoCount;
        return calculateYieldPercentage(totalPrize, totalSpent);
    }

    private long calculateTotalPrize() {
        return matchCounts.entrySet().stream()
                .filter(entry -> prizeMap.containsKey(entry.getKey()))
                .mapToLong(entry -> entry.getValue() * prizeMap.get(entry.getKey()))
                .sum();
    }

    private double calculateYieldPercentage(long totalPrize, long totalSpent) {
        if (totalSpent > 0) {
            return (double) totalPrize / totalSpent * 100;
        }
        return 0;
    }

}
