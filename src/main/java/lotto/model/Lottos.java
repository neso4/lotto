package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> saveRankResult(User user) {
        Map<Rank, Integer> rankResult = initRank();
        for (Lotto lotto : lottos) {
            Rank rank = user.getRank(lotto);
            rankResult.computeIfPresent(rank, (key, count) -> ++count);
        }
        return rankResult;
    }

    private Map<Rank, Integer> initRank() {
        Map<Rank, Integer> rankResult = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankResult.put(rank, 0);
        }
        return rankResult;
    }

}
