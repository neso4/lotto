package lotto.domain;

import lotto.domain.enums.LottoRank;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoResult = new EnumMap<>(LottoRank.class);

    public LottoResult(List<LottoRank> ranks) {
        initLottoResult();
        putRankCount(ranks);
    }

    private void initLottoResult() {
        for (LottoRank rank : LottoRank.values()) {
            lottoResult.put(rank, 0);
        }
    }

    private void putRankCount(List<LottoRank> ranks) {
        for (LottoRank rank : ranks) {
            lottoResult.put(rank, lottoResult.getOrDefault(rank, 0) + 1);
        }
    }

    public double getEarningRate(long money) {
        return ((double) sumPrize() / money) * 100;
    }

    private long sumPrize() {
        return lottoResult.entrySet().stream()
                .mapToLong(entry -> entry.getKey().calculate(entry.getValue()))
                .sum();
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return lottoResult;
    }
}
