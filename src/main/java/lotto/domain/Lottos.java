package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import lotto.domain.strategy.IssuableStrategy;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final int totalCount, final IssuableStrategy issuableStrategy) {
        this.lottos = createLottos(totalCount, issuableStrategy);
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private List<Lotto> createLottos(final int totalCount, final IssuableStrategy issuableStrategy) {
        return IntStream.rangeClosed(1, totalCount)
                .mapToObj(count -> issuableStrategy.issue())
                .toList();
    }

    public int count() {
        return lottos.size();
    }

    public EnumMap<Rank, Integer> getRankResult(final WinningLotto winningLotto) {
        EnumMap<Rank, Integer> rankResult = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            int matchingCount = winningLotto.countMatchingNumber(lotto);
            boolean bonusNumberExistence = winningLotto.hasBonusNumber(lotto);
            Rank result = Rank.find(matchingCount, bonusNumberExistence);
            rankResult.put(result, rankResult.getOrDefault(result, 0) + 1);
        }
        return rankResult;
    }

    public long calculateTotalReward(final EnumMap<Rank, Integer> rankResult) {
        long totalReward = 0L;
        for (Entry<Rank, Integer> rankResultEntry : rankResult.entrySet()) {
            totalReward += calculateReward(rankResultEntry.getKey(), rankResultEntry.getValue());
        }
        return totalReward;
    }

    private long calculateReward(final Rank rank, final int count) {
        return rank.totalReward(rank, count);
    }

}
