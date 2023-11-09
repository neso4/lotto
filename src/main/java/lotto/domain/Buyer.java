package lotto.domain;

import lotto.domain.constants.LottoRank;

import java.util.Map;

public class Buyer {
    private Lottos lottos;

    public Buyer(Lottos lottos) {
        this.lottos = lottos;
    }

    public int lottoCount() {
        return this.lottos.lottoCount();
    }

    public Map<LottoRank, Integer> confirmLotto(WinningLotto winningLotto) {
        return lottos.winningLottoCompareLottos(winningLotto);
    }

    @Override
    public String toString() {
        return lottos.toString();
    }
}