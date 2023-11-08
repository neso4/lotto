package lotto.controller;

import lotto.domain.*;
import lotto.view.*;

import java.util.List;

public class LottoController {
    public void run() {
        Money money = getInputMoney();
        Lottos lottos = getLottos(money.getTicket());

        OutputView.printBuyLotto(money);
        OutputView.printLottos(lottos);

        WinningNumber winningNumber = getWinningNumber();
        RankResult rankResult = new RankResult();
        calcLottoResult(rankResult, winningNumber, lottos);

        Rate rate = getRate(money, rankResult);
        OutputView.printResult(rankResult, rate);
    }

    private Money getInputMoney() {
        InputMoneyView inputMoneyView = new InputMoneyView();
        String inputMoney = inputMoneyView.getInputMoney();
        int money = Integer.parseInt(inputMoney);
        return new Money(money);
    }

    private Lottos getLottos(int ticket) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        return new Lottos(lottoGenerator.generateLottos(ticket));
    }

    private WinningNumber getWinningNumber() {
        InputWinningNumberView inputWinningNumberView = new InputWinningNumberView();
        InputBonusNumberView inputBonusNumberView = new InputBonusNumberView();

        List<Integer> winningNumbers = inputWinningNumberView.getWinningNumber();
        int bonusNumber = inputBonusNumberView.getBonusNumber(winningNumbers);

        return new WinningNumber(winningNumbers, bonusNumber);
    }

    private void calcLottoResult(RankResult rankResult, WinningNumber winningNumber, Lottos lottos) {
        rankResult.calcRankResult(winningNumber, lottos);
    }

    private Rate getRate(Money money, RankResult rankResult) {
        return new Rate(money, rankResult);
    }
}
