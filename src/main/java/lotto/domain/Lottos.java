package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public void printLottos() {
        for (Lotto lotto : lottos) {
            lotto.printLotto();
            System.out.println();
        }
    }
}
