package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.util.Constants;

public class Result {
    private static final int INCREASE_AMOUNT = 1;
    private final Map<LottoRanking, Integer> lottoResult = new EnumMap<>(LottoRanking.class);

    public Result(List<LottoRanking> lottoRankingList) {
        setResult();
        createResult(lottoRankingList);
    }

    public int calculateTotalPrize() {
        return lottoResult.entrySet().stream()
                .filter(entry -> entry.getKey() != LottoRanking.NO_MATCH)
                .mapToInt(entry -> entry.getKey().
                        getPrizeAmount() * entry.getValue())
                .sum();
    }

    public Map<LottoRanking, Integer> getLottoResult() {
        return Collections.unmodifiableMap(lottoResult);
    }

    private void setResult() {
        for (LottoRanking rank : LottoRanking.values()) {
            lottoResult.put(rank, Constants.ZERO);
        }
    }

    private void createResult(List<LottoRanking> lottoRankingList) {
        for (LottoRanking lottoRanking : lottoRankingList) {
            int increaseCount = lottoResult.get(lottoRanking) + INCREASE_AMOUNT;
            lottoResult.put(lottoRanking, increaseCount);
        }
    }
}
