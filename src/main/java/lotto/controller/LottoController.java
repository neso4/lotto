package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.application.LottoMachine;
import lotto.domain.Amount;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.dto.WinningLotto;
import lotto.dto.WinningResult;
import lotto.ui.InputView;
import lotto.ui.OutputView;
import lotto.util.Converter;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(final InputView inputView, final OutputView outputView, final LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        Amount amount = this.getAmount();
        LottoTicket lottoTicket = this.buyLottoTicket(amount);
        outputView.printLottoTicket(lottoTicket);

        WinningLotto winningLotto = this.issueWinningLotto();
        WinningResult winningResult = lottoTicket.match(winningLotto);
        outputView.printWinningResult(winningResult);

        final double yield = amount.calculateYield(winningResult.totalWinningAmount());
        outputView.printYield(Converter.convertToStringWithRound(yield));
    }

    private Amount getAmount() {
        return this.inputWithRetry(() -> {
            String input = inputView.scanAmount();
            int amount = Converter.convertToInt(input);
            return new Amount(amount);
        });
    }

    private LottoTicket buyLottoTicket(final Amount amount) {
        int quantity = amount.getLottoQuantity();
        return lottoMachine.createLottoTicketByAuto(quantity);
    }

    private WinningLotto issueWinningLotto() {
        Lotto lotto = this.getLotto();
        return this.inputWithRetry(() -> {
            LottoNumber bonus = this.getBonus();
            return new WinningLotto(lotto, bonus);
        });
    }

    private Lotto getLotto() {
        return this.inputWithRetry(() -> {
            String input = inputView.scanWinningLottoNumber();
            List<Integer> numbers = Converter.convertToIntListByDelimiter(input);
            return lottoMachine.createLotto(numbers);
        });
    }

    private LottoNumber getBonus() {
        String input = inputView.scanBonusNumber();
        int bonus = Converter.convertToInt(input);
        return lottoMachine.createLottoNumber(bonus);
    }

    private <T> T inputWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
