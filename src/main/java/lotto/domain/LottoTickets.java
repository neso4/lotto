package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<Lotto> lottos;

    public LottoTickets(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Lotto, Rank> findRankByLotto(Lotto winningLotto, Bonus bonus) {
        return lottos.stream()
                .collect(Collectors.toMap(
                        lotto -> lotto,
                        lotto -> Rank.find(lotto.countWinningNumber(winningLotto), lotto.hasBonusNumber(bonus))
                ));
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
