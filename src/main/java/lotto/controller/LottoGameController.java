package lotto.controller;

import java.util.List;
import lotto.result.Calculator;
import lotto.util.Generator;
import lotto.input.InputHandler;
import lotto.Lotto;
import lotto.matching.Matcher;
import lotto.result.Result;

public class LottoGameController {

    private final Generator lottoGenerator = new Generator();
    private final Matcher matcher = new Matcher();
    private final InputHandler inputHandler = new InputHandler();
    private final Calculator calculator = new Calculator();

    public void run() {
        int purchasedPrice = inputHandler.getPurchasedPrice();
        List<Lotto> lottoList = lottoGenerator.getLotto(purchasedPrice);
        Lotto winningLotto = inputHandler.getWinningLotto();
        int bonus = inputHandler.getBonusNumber(winningLotto);

        Result result = matcher.matchWinning(winningLotto, lottoList, bonus);

        calculator.getStatistics(result.getMap());
        calculator.getProfitRate(result.getMap(), purchasedPrice);
    }
}
