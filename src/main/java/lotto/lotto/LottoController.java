package lotto.lotto;

import java.util.List;
import lotto.bonus.BonusNumber;
import lotto.money.Money;
import lotto.ranking.Ranking;
import lotto.statistics.Statistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = receiveMoney();
        LottoPaper lottoPaper = buyLottoPaper(money);
        aggregateResult(lottoPaper, money);
    }

    private LottoPaper buyLottoPaper(Money money) {
        LottoPaper lottoPaper = new LottoPaper(makeLottoPaper(money));
        OutputView.buyLottoPaper(money, lottoPaper);
        return lottoPaper;
    }

    private List<Lotto> makeLottoPaper(Money money) {
        return lottoService.makeLottoPaper(money);
    }

    private Money receiveMoney() {
        try {
            OutputView.inputMoney();
            return InputView.getMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return receiveMoney();
        }
    }

    private WinningLotto receiveWinningLotto() {
        try {
            OutputView.inputWinningLotto();
            return InputView.getWinnerLotto();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return receiveWinningLotto();
        }
    }

    private BonusNumber receiveBonusNumber() {
        try {
            OutputView.inputBonusNumber();
            return InputView.getBonusNumber();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return receiveBonusNumber();
        }
    }

    private void aggregateResult(LottoPaper lottoPaper, Money money) {
        WinningLotto winningLotto = receiveWinningLotto();
        BonusNumber bonusNumber = receiveBonusNumber();
        Statistics results = calculateStatistics(lottoPaper, winningLotto, bonusNumber);
        showStatistics(money, results);
    }

    private Statistics calculateStatistics(LottoPaper lottoPaper, WinningLotto winningLotto, BonusNumber bonusNumber) {
        List<Ranking> rankings = lottoPaper.calculateRankings(winningLotto, bonusNumber);
        return new Statistics(rankings);
    }

    private void showStatistics(Money money, Statistics statistics) {
        OutputView.showStatists(statistics, money);
    }
}
