package lotto.controller;

import java.util.List;
import lotto.domain.Bonus;
import lotto.domain.LottoManager;
import lotto.domain.MatchNumber;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.dto.AutoLottoDto;
import lotto.utils.LottoCaclulator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.Viewable;

public class GameController {
    private final LottoManager lottoManager;
    private Money money;
    private static final Viewable inputView = new InputView().getInstance();

    public GameController(final LottoManager lottoManager) {
        this.lottoManager = lottoManager;
    }

    public void run() {
        money = inputView.getMoneyInput();
        lottoManager.buyAutoLottos(money.requestLottoCount());
        showBought();

        WinningLotto winningLotto = inputView.getWinningInput();
        OutputView.printEmpty();
        Bonus bonus = inputView.getBonusInput();

        lottoManager.createWinning(winningLotto, bonus);
        showResult();
    }

    private void showBought() {
        OutputView.printEmpty();
        OutputView.printAutoLottos(AutoLottoDto.fromEnity(lottoManager.getAutoLottos()), lottoManager.getLottoCount());
        OutputView.printEmpty();
    }

    private void showResult() {
        OutputView.printEmpty();
        List<MatchNumber> resultLottoToMatch = lottoManager.judgeMatchNumberByLotto();
        List<Integer> countPerMatchNumber = lottoManager.totalCountOfMatchNumber(resultLottoToMatch);
        OutputView.printResultCount(countPerMatchNumber);
        double rateOfReturn = LottoCaclulator.calculateRateOfReturn(countPerMatchNumber, money.getMoney());
        OutputView.printRateOfResult(rateOfReturn);
    }

}
