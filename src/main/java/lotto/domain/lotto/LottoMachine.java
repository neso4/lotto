package lotto.domain.lotto;

import static lotto.util.ConstantUtils.*;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.amount.PurchaseAmount;

public class LottoMachine {

    private final List<Lotto> lottos;

    private LottoMachine() {
        this.lottos = new ArrayList<>();
    }

    public static LottoMachine initial() {
        return new LottoMachine();
    }

    public Lottos purchaseLottos(PurchaseAmount purchaseAmount) {
        while (purchaseAmount.isEnough()) {
            purchaseAmount = buyLotto(purchaseAmount);
        }
        return Lottos.from(Collections.unmodifiableList(lottos));
    }

    private PurchaseAmount buyLotto(PurchaseAmount purchaseAmount) {
        lottos.add(Lotto.from(Randoms.pickUniqueNumbersInRange(LOTTO_START_INCLUSIVE,
            LOTTO_END_INCLUSIVE,
            LOTTO_SIZE_CRITERION)));
        return purchaseAmount.subtractLottoCost();
    }
}
