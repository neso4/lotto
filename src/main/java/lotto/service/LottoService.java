package lotto.service;

import java.util.Collections;
import lotto.constant.PurchaseAmount;
import lotto.domain.Lotto;
import lotto.domain.numbergenerator.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    private final RandomLottoNumberGenerator randomLottoNumberGenerator;

    public LottoService(RandomLottoNumberGenerator randomLottoNumberGenerator) {
        this.randomLottoNumberGenerator = randomLottoNumberGenerator;
    }

    public List<Lotto> buyLottos(long purchaseAmount) {
        long lottoQuantity = getLottoQuantity(purchaseAmount);
        return getLottos(lottoQuantity);
    }

    private static long getLottoQuantity(long purchaseAmount) {
        return purchaseAmount / PurchaseAmount.UNIT.getAmount();
    }

    private List<Lotto> getLottos(long lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        getLotto(lottoQuantity, lottos);
        return Collections.unmodifiableList(lottos);
    }

    private void getLotto(long lottoQuantity, List<Lotto> lottos) {
        for (int i = 0; i < lottoQuantity; i++) {
            lottos.add(new Lotto(randomLottoNumberGenerator.generate()));
        }
    }
}
