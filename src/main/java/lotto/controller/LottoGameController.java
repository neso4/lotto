package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.util.StringToListConverter;
import lotto.util.WinningNumbersValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final OutputView outputView;
    private final InputView inputView;
    private int amount;
    private Lottos lottos;
    private Lotto winningNumbers;
    private BonusNumber bonusNumber;

    public LottoGameController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
    }

    public void run() {
        purchaseLotto();
        winningNumbers = setWinningNumbers();
        bonusNumber = setBonusNumber(winningNumbers);
    }

    private BonusNumber setBonusNumber(Lotto winningNumbers) {
        try {
            outputView.printInputBonusNumber();
            String inputBonusNumber = inputView.inputBonusNumber();
            return new BonusNumber(inputBonusNumber, winningNumbers);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }

        return setBonusNumber(winningNumbers);
    }

    private Lotto setWinningNumbers() {
        try {
            outputView.printInputWinningNumbers();
            String inputWinningNumbers = inputView.inputWinningNumbers();
            WinningNumbersValidator.validate(inputWinningNumbers);
            List<Integer> numbers = StringToListConverter.convert(inputWinningNumbers);
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }

        return setWinningNumbers();
    }

    private void purchaseLotto() {
        PurchaseAmount purchaseAmount = setPurchaseAmount();
        amount = purchaseAmount.getAmount();
        lottos = createLottos(purchaseAmount.getNumberOfLotto());
        outputView.printLottos(lottos);
    }

    private Lottos createLottos(int numberOfLotto) {
        return new Lottos(numberOfLotto);
    }

    public PurchaseAmount setPurchaseAmount() {
        try {
            outputView.printInputPurchaseAmount();
            String amount = inputView.inputPurchaseAmount();
            return new PurchaseAmount(amount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return setPurchaseAmount();
    }
}
