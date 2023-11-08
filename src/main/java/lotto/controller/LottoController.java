package lotto.controller;

import lotto.domain.Amount;
import lotto.domain.BonusNumber;
import lotto.domain.JudgeCounter;
import lotto.domain.LottoJudge;
import lotto.domain.WinnerNumbers;
import lotto.domain.LottoTicket;

import lotto.view.input.AmountView;
import lotto.view.input.BonusNumberView;
import lotto.view.input.Input;
import lotto.view.input.WinnerNumberView;
import lotto.view.output.LottoStatisticsView;
import lotto.view.output.LottoView;
import lotto.view.output.Output;

public class LottoController {
    private LottoTicket lottoTicket;
    private Amount amount;
    private BonusNumber bonusNumber;
    private WinnerNumbers winnerNumbers;
    private JudgeCounter judgeCounter;

    public LottoController() {
    }

    public void run() {
        manageAmount();
        createLottoTicket();
        displayMyLotto();
        manageWinnerNumber();
        manageBonusNumber();
        createWinningResult();
        displayLottoStatistics();
    }

    private void manageAmount() {
        String input;
        do {
            input = inputAmount();
        } while (attemptCreateAmount(input));
    }

    private String inputAmount() {
        Input amountView = new AmountView();
        return amountView.getInput();
    }

    private boolean attemptCreateAmount(String inputAmount) {
        try {
            this.amount = new Amount(inputAmount);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void createLottoTicket() {
        try {
            this.lottoTicket = new LottoTicket();
            this.lottoTicket.addLotto(amount.getAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayMyLotto() {
        Output lottoView = new LottoView(this.lottoTicket.getLottos());
        lottoView.println();
        lottoView.getOutput();
    }

    private void manageWinnerNumber() {
        String input;
        do {
            input = inputWinnerNumbers();
        } while (attemptCreateWinnerNumber(input));
    }

    private String inputWinnerNumbers() {
        Input winnerNumberView = new WinnerNumberView();
        winnerNumberView.println();
        return winnerNumberView.getInput();
    }

    private boolean attemptCreateWinnerNumber(String inputWinnerNumber) {
        try {
            this.winnerNumbers = new WinnerNumbers(inputWinnerNumber);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void manageBonusNumber() {
        String input;
        do {
            input = inputBonusNumbers();
        } while (attemptCreateBonusNumber(input));
    }

    private String inputBonusNumbers() {
        Input BonusNumberView = new BonusNumberView();
        BonusNumberView.println();
        return BonusNumberView.getInput();
    }

    private boolean attemptCreateBonusNumber(String inputBonusNumber) {
        try {
            this.bonusNumber = new BonusNumber(inputBonusNumber, this.winnerNumbers);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private void createWinningResult() {
        LottoJudge lottoJudge = new LottoJudge();
        this.judgeCounter = lottoJudge.judgeLottoTicket(this.lottoTicket, this.winnerNumbers, this.bonusNumber);
    }

    private void displayLottoStatistics() {
        Output statisticsView = new LottoStatisticsView(this.judgeCounter, this.amount);
        statisticsView.println();
        statisticsView.getOutput();
    }
}
