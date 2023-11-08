package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoNumberGenerator;
import lotto.model.LottoResult;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private long purchaseAmount;
    private final List<Lotto> lottoTickets = new ArrayList<>();
    private WinningNumbers winningNumbers;

    public void purchase() {
        purchaseAmount = InputView.inputPurchaseAmount();
        long tickets = purchaseAmount / 1000;
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        for (int i = 0; i < tickets; i++) {
            lottoTickets.add(new Lotto(lottoNumberGenerator.generate()));
        }

        OutputView.printPurchaseResult(tickets, lottoTickets);
    }

    public void setWinningNumbers() {
        List<Integer> numbersInput = InputView.inputWinningNumbers();
        int numberInput = InputView.inputBonusNumber(numbersInput);
        winningNumbers = WinningNumbers.getInstance(numbersInput, numberInput);
    }

    public void makeResult() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.makeResult(lottoTickets, winningNumbers);

        OutputView.printResult(lottoResult, purchaseAmount);
    }
}
