package lotto.data;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {

    List<Lotto> lottos = new ArrayList<>();
    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
