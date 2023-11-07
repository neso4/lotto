package lotto;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.dto.BuyingResults;
import lotto.dto.WinningResults;
import lotto.generator.RandomLottoNumberGenerator;
import lotto.generator.WinningResultMessageGenerator;
import lotto.resolver.ExceptionResolver;
import lotto.view.InputViewable;
import lotto.view.OutputView;

public class LottoGame {

    private final InputViewable inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoGame(final InputViewable inputView, final OutputView outputView, final LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void startGame() {
        ExceptionResolver.resolveProcess(LottoGame::buyLottos, this);
        printBuyingResults();
        ExceptionResolver.resolveProcess(LottoGame::inputWinningLotto, this);
        printWinningResult();
    }

    private void buyLottos() {
        int price = inputView.inputBuyingPrice();
        lottoMachine.buyLottos(RandomLottoNumberGenerator.getSupplier(), price);
    }

    private void inputWinningLotto() {
        List<Integer> winningNumbers = inputView.inputLottoNumbers();
        int bonusNumber = inputView.inputBonusNumber();
        lottoMachine.addWinningLotto(winningNumbers, bonusNumber);
    }

    private void printWinningResult() {
        WinningResults winningResults = lottoMachine.createWinningResults();
        String message = WinningResultMessageGenerator.generate(winningResults);
        outputView.printWinningResult(message);
    }

    private void printBuyingResults() {
        BuyingResults buyingResults = lottoMachine.createBuyingResults();
        outputView.printBuyingResults(buyingResults.getBuyingCount(), buyingResults.createResultMessage());
    }
}
