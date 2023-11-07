package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;
public class LottoController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        buy();
        setWinning();
        compare();
    }

    private void buy() {
        outputView.printInputMoneyToBuyLottoMessage();
        Money money = inputView.readMoneyToBuyLotto();
        lottoService.buyLotto(money);
        outputView.printBoughtLottoCount(money.convertMoneyToCount());
        outputView.printLottos(lottoService.findBoughtLottos());
    }

    private void setWinning() {
        outputView.printInputWinningNumbersMessage();
        WinningNumbers winningNumbers = inputView.readWinningNumbers();
        outputView.printInputBonusNumberMessage();
        BonusNumber bonusNumber = inputView.readBonusNumber();
        lottoService.setWiningNumbers(winningNumbers, bonusNumber);
    }

    private void compare() {
        outputView.printWinningResultMessage();
        lottoService.compareAllLotto();
    private Map<WinningGrade, Integer> getWinningResult() {
    private double getCalculatedLottoYield() {
        return lottoService.calculateLottoYield();
    }
}
