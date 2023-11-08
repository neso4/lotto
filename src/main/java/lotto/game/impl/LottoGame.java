package lotto.game.impl;

import lotto.domain.Statistics;
import lotto.vo.Wallet;
import lotto.domain.WinningLotto;
import lotto.game.Game;
import lotto.game.controller.MoneyController;
import lotto.game.controller.PrintController;
import lotto.game.controller.StatisticsController;
import lotto.game.controller.WalletController;
import lotto.game.controller.WinningLottoController;
import lotto.vo.Money;

public class LottoGame implements Game {

    private final MoneyController moneyController;
    private final WinningLottoController winningLottoController;
    private final StatisticsController statisticsController;
    private final PrintController printController;
    private final WalletController walletController;

    public LottoGame(MoneyController moneyController, WinningLottoController winningLottoController,
                     StatisticsController statisticsController, PrintController printController,
                     WalletController walletController) {
        this.moneyController = moneyController;
        this.winningLottoController = winningLottoController;
        this.statisticsController = statisticsController;
        this.printController = printController;
        this.walletController = walletController;
    }

    @Override
    public void playLotto() {
        printController.purchaseAmount();
        Money money = moneyController.make();
        printController.newLine();
        printController.LottoCount(money);
        Wallet wallet = walletController.make(money);
        printController.newLine();
        WinningLotto winningLotto = winningLottoController.make();
        Statistics statistics = statisticsController.make(wallet, winningLotto);
        printController.statistics(statistics);
        printController.rateOfReturn(money, statistics);
    }
}
