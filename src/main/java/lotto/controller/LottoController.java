package lotto.controller;

import static lotto.view.OutputView.printByLottoCount;
import static lotto.view.OutputView.printLottos;
import static lotto.view.OutputView.printWinningResultAndRateOfReturn;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.LottosGeneratorMachine;
import lotto.model.Money;
import lotto.model.RateOfReturn;
import lotto.model.WinningLotto;
import lotto.model.WinningResult;
import lotto.model.WinningStandard;
import lotto.view.InputView;

public class LottoController {

    private static WinningLotto getWinningLotto(Lotto lotto, int bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public void start() {
        Money money = getValidMoney();
        printByLottoCount(money);

        Lottos lottos = generateLottos(money);
        printLottos(lottos);

        Lotto validWinningLotto = getValidLotto();
        WinningLotto validWinningLotto1 = getValidWinningLotto(validWinningLotto);
        WinningResult winningResult = calculateWinningResult(lottos, validWinningLotto1);

        double rateOfReturn = getRateOfReturn(money, winningResult);
        printWinningResultAndRateOfReturn(winningResult, rateOfReturn);

    }

    private Money getValidMoney() {
        Money money = null;
        while (money == null) {
            try {
                int userInputForLottoBuyAccount = InputView.getUserInputForLottoBuyAccount();
                money = getMoney(userInputForLottoBuyAccount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return money;
    }

    private Money getMoney(int money) {
        return new Money(money);
    }

    private Lotto getValidLotto() {
        Lotto lotto = null;
        while (lotto == null) {
            try {
                List<Integer> winningNumbers = InputView.getUserInputForWinningNumbers();
                lotto = getLotto(winningNumbers);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto;
    }

    private Lotto getLotto(List<Integer> winningNumbers) {
        return new Lotto(winningNumbers);
    }

    private WinningLotto getValidWinningLotto(Lotto lotto) {
        WinningLotto winningLotto = null;
        while (winningLotto == null) {
            try {
                int bonusNumber = InputView.getUserInputForBonusNumber();
                winningLotto = getWinningLotto(lotto, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningLotto;
    }

    private double getRateOfReturn(Money money, WinningResult winningResult) {
        RateOfReturn rateOfReturn = new RateOfReturn(money, winningResult);
        return rateOfReturn.getRateOfReturn();
    }

    private WinningResult calculateWinningResult(Lottos lottos, WinningLotto winningLotto) {
        List<Lotto> purchasedLottos = lottos.getLottos();
        WinningResult winningResult = new WinningResult();

        for (Lotto lotto : purchasedLottos) {
            WinningStandard ranking = WinningStandard.checkMatchNumbers(lotto, winningLotto);
            winningResult.addResult(ranking, 1);
        }
        return winningResult;
    }

    private Lottos generateLottos(Money money) {
        LottosGeneratorMachine lottosGeneratorMachine = new LottosGeneratorMachine();
        return new Lottos(lottosGeneratorMachine.generateLottos(money.getLottoCount()));
    }
}
