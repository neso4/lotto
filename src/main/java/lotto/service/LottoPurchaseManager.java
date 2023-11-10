package lotto.service;

import lotto.domain.LottoPurchaseAmount;
import lotto.domain.Lottos;
import lotto.utils.LottoGenerator;

import static lotto.constants.LottoConstants.LOTTO_PRICE;

public class LottoPurchaseManager {
    private final LottoPurchaseAmount purchaseAmount;
    private final Lottos lottos;

    private LottoPurchaseManager(LottoPurchaseAmount lottoPurchaseAmount) {
        this.purchaseAmount = lottoPurchaseAmount;
        lottos = createLottos();
    }

    public static LottoPurchaseManager from(LottoPurchaseAmount lottoPurchaseAmount) {
        return new LottoPurchaseManager(lottoPurchaseAmount);
    }

    private Lottos createLottos() {
        long quantity = calculateLottoQuantity();
        return LottoGenerator.generateLottos(quantity);
    }

    public LottoPurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public Lottos getLottos() {
        return lottos;
    }

    private long calculateLottoQuantity() {
        return purchaseAmount.getAmount() / LOTTO_PRICE;
    }
}
