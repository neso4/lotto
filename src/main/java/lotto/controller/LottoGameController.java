package lotto.controller;

import java.math.BigDecimal;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.LottoGenerator;
import lotto.model.LottoPublisher;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.WinningResult;
import lotto.model.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    public void run() {
        final Money money = readPurchaseAmount();
        final List<Lotto> lottoTickets = publishLotto(money);
        OutputView.printLottoTickets(lottoTickets);

        final LottoGame lottoGame = new LottoGame(readWinningNumber(), lottoTickets);
        final WinningResult winningResult = lottoGame.play();
        OutputView.printWinningResult(winningResult);

        final BigDecimal profitRate = winningResult.calculateProfitRate(money);
        OutputView.printProfitRate(profitRate);
    }

    private Money readPurchaseAmount() {
        return InputView.readWithRetry(this::createPurchaseAmount);
    }

    private Money createPurchaseAmount() {
        return Money.of(InputView.readPurchaseAmount());
    }

    private List<Lotto> publishLotto(final Money money) {
        final LottoPublisher publisher = createLottoPublisher();
        return publisher.publish(money);
    }

    private LottoPublisher createLottoPublisher() {
        return new LottoPublisher(createLottoGenerator());
    }

    private LottoGenerator createLottoGenerator() {
        return new RandomLottoGenerator();
    }

    private WinningNumber readWinningNumber() {
        return InputView.readWithRetry(this::createWinningNumber);
    }

    private WinningNumber createWinningNumber() {
        return new WinningNumber(
                InputView.readWinningNumbers(),
                InputView.readBonusNumber()
        );
    }
}
