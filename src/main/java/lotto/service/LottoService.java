package lotto.service;

import lotto.domain.PurchasedLottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Lotto;
import lotto.domain.BonusNumber;
import lotto.domain.WinningLotto;

public class LottoService {
    private PurchaseAmount purchaseAmount;
    private PurchasedLottos purchasedLottos;
    private Lotto winningLotto;
    private BonusNumber bonusNumber;

    public void setPurchasedLottos(String inputAmount) {
        this.purchaseAmount = new PurchaseAmount(inputAmount);
        this.purchasedLottos = new PurchasedLottos(this.purchaseAmount);
    }

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public PurchasedLottos getPurchasedLottos() {
        return purchasedLottos;
    }

    public void setWinningLotto(String inputWinningNumbers) {
        WinningLotto winningLotto = new WinningLotto(inputWinningNumbers);
        this.winningLotto = winningLotto.getLotto();
    }

    public void setBonusNumber(String inputBonusNumber) {
        this.bonusNumber = new BonusNumber(inputBonusNumber);
    }
}
