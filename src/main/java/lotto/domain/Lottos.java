package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.NumberPickingStrategy;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public Lotto createLotto(NumberPickingStrategy numberPickingStrategy) {
        Lotto lotto = new Lotto(numberPickingStrategy.pickLottoNumbers());
        lottos.add(lotto);
        return lotto;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
