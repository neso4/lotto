package lotto;


import lotto.v3.controller.*;
import lotto.v3.model.Lotto;
import lotto.v3.model.LottoMachine;
import lotto.v3.model.LottoWinningNumber;
import lotto.v3.view.LottoBonusNumberView;
import lotto.v3.view.LottoGenerateView;
import lotto.v3.view.LottoPurchaseView;
import lotto.v3.view.LottoWinningNumbersView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseView purchaseView = new LottoPurchaseView();
        LottoPurchaseController purchaseController = new LottoPurchaseController(purchaseView);

        LottoGenerateView generateView = new LottoGenerateView();
        LottoMachine machine = new LottoMachine();
        LottoGenerationController generationController = new LottoGenerationController(generateView, machine);

        int numberOfTickets = purchaseController.startPurchaseProcess();
        List<Lotto> tickets = generationController.generateLottoTickets(numberOfTickets);

        LottoWinningNumbersView winningNumbersView = new LottoWinningNumbersView();
        LottoWinningNumberController winningNumberController = new LottoWinningNumberController(winningNumbersView);
        LottoWinningNumber winningNumber = winningNumberController.createWinningNumber();

        LottoBonusNumberView bonusNumberView = new LottoBonusNumberView();
        LottoBonusNumberController bonusNumberController = new LottoBonusNumberController(bonusNumberView, winningNumber);
        bonusNumberController.createBonusNumber();

    }
}
