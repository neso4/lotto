package lotto;

import lotto.controller.LottoController;
import lotto.model.Jackpot;
import lotto.model.Lotto;
import lotto.model.Purchase;
import lotto.model.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        // 로또 구매
        Purchase purchase = purchaseLottsProcess(lottoController, inputView, outputView);
        int purchaseCount = purchase.getPurchaseCount();

        // 로또 발급
        List<Lotto> lottos = lottoController.generateLottoTicket(purchaseCount);
        outputView.printLottos(lottos);

        // 당첨 및 보너스 번호 생성
        String winningNumbers = inputView.inputWinningNumbers();
        String bonusNumber = inputView.inputBonusNumber();
        Jackpot jackpot = lottoController.createWinningNumbers(winningNumbers, bonusNumber);

        // 발행된 로또들의 당첨 여부 계산
        Result result = lottoController.createResult(jackpot, lottos, purchase);
        lottoController.calculateWinningRanks(result);
        outputView.printWinningStatistics(result);

        // 발행된 로또들의 수익률 계산
        Double rate = lottoController.calculateProfitRate(result);
        outputView.printProfitRate(rate);
    }

    private static Purchase purchaseLottsProcess(LottoController lottoController, InputView inputView, OutputView outputView) {
        Purchase purchase = null;
        boolean isValidateInput = false;

        while (!isValidateInput){
            try {
                String amount = inputView.inputPurchaseAmount();
                purchase = lottoController.purcahseLottos(amount);
                outputView.printPurchaseCount(purchase.getPurchaseCount());
                isValidateInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return purchase;
    }
}
