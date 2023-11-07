package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final EnumMap<LottoRanking, Integer> lottoRankingResult;

    private LottoResult(EnumMap<LottoRanking, Integer> lottoRankingResult) {
        this.lottoRankingResult = lottoRankingResult;
    }

    public static LottoResult createByRankings(List<LottoRanking> rankings) {
        EnumMap<LottoRanking, Integer> lottoRankingResult = new EnumMap<>(LottoRanking.class);

        for (LottoRanking ranking : rankings) {
            Integer prevCount = lottoRankingResult.getOrDefault(ranking, 0);
            lottoRankingResult.put(ranking, prevCount + 1);
        }

        return new LottoResult(lottoRankingResult);
    }

    public double calculateProfit(int purchasedAmount) {
        long totalPrize = lottoRankingResult.keySet().stream()
                .mapToLong(ranking -> {
                    Integer count = lottoRankingResult.getOrDefault(ranking, 0);
                    return ranking.getTotalPrize(count);
                })
                .sum();

        return Math.round(((double) totalPrize / purchasedAmount) * 100 * 100) / 100.0;
    }

    public Map<LottoRanking, Integer> getResult() {
        return Map.copyOf(lottoRankingResult);
    }
}
