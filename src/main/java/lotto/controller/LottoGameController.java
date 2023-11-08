package lotto.controller;

import lotto.controller.inputCreator.BonusNumberController;
import lotto.controller.inputCreator.ObjectCreator;
import lotto.controller.inputCreator.PurchaseAmountController;
import lotto.controller.inputCreator.WinningLottoController;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Income;
import lotto.domain.PurchaseAmount;
import lotto.domain.PurchasedLotto;
import lotto.view.OutputView;

public class LottoGameController {
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoResultService lottoResultService;

    public LottoGameController(
            LottoPurchaseService lottoPurchaseService,
            LottoResultService lottoResultService
    ) {
        this.lottoPurchaseService = lottoPurchaseService;
        this.lottoResultService = lottoResultService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = createUntilInputSuccess(new PurchaseAmountController(), null);
        PurchasedLotto purchasedLotto = purchaseLotto(purchaseAmount);
        Lotto winningLotto = createUntilInputSuccess(new WinningLottoController(), null);
        BonusNumber bonusNumber = createUntilInputSuccess(new BonusNumberController(), winningLotto);

        Income income = lottoResultService.createLottoIncome(purchasedLotto, winningLotto, bonusNumber);
        printResult(income, purchaseAmount);
    }

    private PurchasedLotto purchaseLotto(PurchaseAmount purchaseAmount) {
        lottoPurchaseService.printPurchasedLottoCount(purchaseAmount.getLottoCount());
        PurchasedLotto purchasedLotto = lottoPurchaseService.createPurchasedLotto(purchaseAmount);
        lottoPurchaseService.printPurchasedLotto(purchasedLotto);
        return purchasedLotto;
    }

    private void printResult(Income income, PurchaseAmount purchaseAmount) {
        lottoResultService.printMessageBeforeResult();
        lottoResultService.printWinningStatistics(income, purchaseAmount);
        lottoResultService.printIncomeRate(income, purchaseAmount);
    }

    private <T, U> T createUntilInputSuccess(ObjectCreator<T, U> creator, U arg) {
        do {
            try {
                return creator.createObjectByInput(arg);
            } catch (IllegalArgumentException e) {
                OutputView.printNewLine();
                OutputView.printMessage(e.getMessage());
            }
        } while (true);
    }
}
