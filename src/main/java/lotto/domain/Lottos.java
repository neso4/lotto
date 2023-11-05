package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {
    private List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createLottos(final int lottoAmount) {
        List<Lotto> lottos = IntStream.range(0, lottoAmount)
                .mapToObj(i -> LottoFactory.createLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public int getLottoAmount() {
        return lottos.size();
    }
}
