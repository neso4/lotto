package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Bonus;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.service.LottoService;
import lotto.utils.Parser;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private PurchaseController purchaseController = new PurchaseController();
    private LottoView lottoView = new LottoView();
    private InitLottoController initLottoController = new InitLottoController();
    private WinningNumberController winningNumberController = new WinningNumberController();
    private BonusNumberController bounusNumberController = new BonusNumberController();

    private int ticketNumber;
    private PurchaseAmount purchaseAmount;
    private List<Lotto> lottos  = new ArrayList<>();
    private int[] winResult;
    private LottoService lottoService = new LottoService();
    public void startLotto(){
        purchaseLotto();
        Lotto winningNumber = inputWinningNumber();
        Bonus bonus = inputBounusNumber(winningNumber);
        processWinningResult(winningNumber,lottos,bonus);
    }

    public void purchaseLotto(){
        purchaseAmount = purchaseController.runPurchaseAmount();
        ticketNumber = purchaseAmount.getTicketNum();
        lottoView.outputPurchaseAmountView(ticketNumber);
        lottos = initLottoController.runInitLotto(ticketNumber);
        lottoView.printPurchaseLotto(lottos);
    }

    public Lotto inputWinningNumber(){
        return winningNumberController.runWinningNumber();
    }

    public Bonus inputBounusNumber(Lotto winningNumber){
        return bounusNumberController.runBonusNumber(winningNumber);
    }
    public void processWinningResult(Lotto winningNumber,List<Lotto> lottos, Bonus bonus){
        List<Integer> countWinningNum;
        String winRate;
        countWinningNum = lottoService.calcWinningResult(winningNumber, lottos);
        winResult = lottoService.calcRanking(countWinningNum, winningNumber, bonus);
        winRate = lottoService.calcWinningRate(winResult,purchaseAmount);
        lottoView.printWinningResult(winResult);
        lottoView.printRate(winRate);
    }
}
