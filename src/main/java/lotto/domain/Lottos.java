package lotto.domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<PrizeCondition> providePrizeConditions(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lotto.getPrizeCondition(winningLotto))
                .toList();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public long getSize() {
        return lottos.size();
    }
}
