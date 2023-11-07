package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public Lotto createLotto(NumberPickingStrategy numberPickingStrategy) {
        Lotto lotto = new Lotto(numberPickingStrategy.pickNumbers());
        lottos.add(lotto);
        return lotto;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
