package lotto.module.manager;

import lotto.module.console.LottoConsoleManager;
import lotto.module.domain.LottoProfit;
import lotto.module.domain.PurchaseAmount;
import lotto.module.lotto.Lotto;
import lotto.module.lotto.UserLottoTickets;
import lotto.module.lotto.WinningLotto;
import lotto.module.result.LottoResult;
import lotto.module.result.LottoResultManager;
import lotto.module.store.LottoStore;

public class LottoManager {
    private final LottoConsoleManager lottoConsoleManager;
    private final LottoResultManager lottoResultManager;
    private final LottoStore lottoStore;

    private LottoManager(LottoConsoleManager lottoConsoleManager, LottoResultManager lottoResultManager, LottoStore lottoStore) {
        this.lottoConsoleManager = lottoConsoleManager;
        this.lottoResultManager = lottoResultManager;
        this.lottoStore = lottoStore;
    }

    public static LottoManager of(LottoConsoleManager lottoConsoleManager, LottoResultManager lottoResultManager, LottoStore lottoStore) {
        return new LottoManager(lottoConsoleManager, lottoResultManager, lottoStore);
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        UserLottoTickets purchasedLottoTickets = purchaseLottoTickets(purchaseAmount);

        Lotto lotto = generateNonBonusNumberWinningNumbers();
        WinningLotto winningLotto = generateWinningNumbers(lotto);

        announceResults(purchaseAmount, purchasedLottoTickets, winningLotto);
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                return lottoConsoleManager.getPurchaseAmount();
            } catch (IllegalArgumentException e) {
                lottoConsoleManager.printErrorMessage(e.getMessage());
            }
        }
    }

    private UserLottoTickets purchaseLottoTickets(PurchaseAmount purchaseAmount) {
        while (true) {
            try {
                UserLottoTickets userLottoTicket = lottoStore.purchaseLotto(purchaseAmount);
                lottoConsoleManager.printPurchasedLottoTickets(userLottoTicket);
                return userLottoTicket;
            } catch (IllegalArgumentException e) {
                lottoConsoleManager.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto generateNonBonusNumberWinningNumbers() {
        while (true) {
            try {
                return lottoConsoleManager.getWinningLottoNumbers();
            } catch (IllegalArgumentException e) {
                lottoConsoleManager.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto generateWinningNumbers(Lotto nonBonusNumberWinningNumbers) {
        while (true) {
            try {
                int lottoBonusNumber = lottoConsoleManager.getLottoBonusNumber();
                return WinningLotto.of(nonBonusNumberWinningNumbers, lottoBonusNumber);
            } catch (IllegalArgumentException e) {
                lottoConsoleManager.printErrorMessage(e.getMessage());
            }
        }
    }

    private void announceResults(PurchaseAmount purchaseAmount, UserLottoTickets purchasedLottoTickets, WinningLotto winningLottoNumbers) {
        LottoResult lottoResult = lottoResultManager.calculateResult(purchasedLottoTickets, winningLottoNumbers);
        LottoProfit lottoProfit = lottoResultManager.calculateProfit(purchaseAmount);
        lottoConsoleManager.printLottoWinningResult(lottoResult, lottoProfit);
    }

}
