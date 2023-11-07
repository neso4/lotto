package lotto.repository;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;

public class LottoRepository {
    private PurchaseAmount purchaseAmount;
    private Lotteries lotteries;

    public LottoRepository() {
        lotteries = new Lotteries();
    }

    public String putPurchaseAmount(String amountInput) {
        purchaseAmount = new PurchaseAmount(amountInput);
        return purchaseAmount.getResult();
    }

    public int getPurchaseQuantity() {
        return purchaseAmount.getQuantity();
    }

    public void insertLotto(Lotto lotto) {
        lotteries.addLotto(lotto);
    }

    public String getLotteriesInfo() {
        return lotteries.concatLotteries();
    }
}
