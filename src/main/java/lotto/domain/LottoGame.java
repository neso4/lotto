package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.util.AutoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private InputView inputView;
    private OutputView outputView;
    private LottoResult lottoResult;
    private PurchaseLottos purchaseLottos;

    public LottoGame() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        PurchasePrice purchasePrice = inputPurchasePrice();
        purchaseLottos = new PurchaseLottos(purchasePrice, new AutoGenerator());

        outputView.printPurchaseLottos(purchaseLottos);

        WinningNumbers winningNumbers = inputWinningNumbers();
        BonusNumber bonusNumber = inputBonusNumber(winningNumbers);

        lottoResult = LottoResult.of(purchaseLottos, winningNumbers, bonusNumber);

        outputView.printLottoResult(lottoResult);
        outputView.printRateOfReturn(lottoResult, purchasePrice);
    }

    private PurchasePrice inputPurchasePrice() {
        PurchasePrice purchasePrice = null;
        while (!isValidPurchasePrice(purchasePrice)) {
            int price = inputView.inputPrice();
            purchasePrice = PurchasePrice.from(price);
        }

        return purchasePrice;
    }

    private boolean isValidPurchasePrice(PurchasePrice purchasePrice) {
        return Objects.nonNull(purchasePrice);
    }

    private WinningNumbers inputWinningNumbers() {
        WinningNumbers winningNumbers = null;
        while (!isValidWinningNumbers(winningNumbers)) {
            List<Integer> inputWinningNumbers = inputView.inputWinningNumbers();
            winningNumbers = WinningNumbers.from(inputWinningNumbers);
        }

        return winningNumbers;
    }

    private boolean isValidWinningNumbers(WinningNumbers winningNumbers) {
        return Objects.nonNull(winningNumbers);
    }

    private BonusNumber inputBonusNumber(WinningNumbers winningNumbers) {
        BonusNumber bonusNumber = null;
        while (!isValidBonusNumber(bonusNumber)) {
            int inputBonusNumber = inputView.inputBonusNumber();
            bonusNumber = BonusNumber.of(inputBonusNumber, winningNumbers);
        }

        return bonusNumber;
    }

    private boolean isValidBonusNumber(BonusNumber bonusNumber) {
        return Objects.nonNull(bonusNumber);
    }
}
