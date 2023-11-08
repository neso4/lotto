package lotto.model;

import java.util.List;
import java.util.function.Consumer;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void printLottos(Consumer<Lotto> printEachLotto) {
        lottos.forEach(printEachLotto);
    }

    public List<Result> getResults(GameNumbers gameNumbers) {
        return lottos.stream()
                .map(lotto -> lotto.checkResult(gameNumbers))
                .toList();
    }
}
