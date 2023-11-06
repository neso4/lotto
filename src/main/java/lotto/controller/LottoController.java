package lotto.controller;

import lotto.model.Jackpot;
import lotto.model.Lotto;
import lotto.model.Purchase;
import lotto.model.Result;

import java.util.List;

public class LottoController {

    // 로또 구매
    public Purchase purcahseLottos(String amount) {
        return Purchase.fromPurchaseAmount(amount);
    }

    // 로또 발급
    public List<Lotto> generateLottoTicket(int purchaseCount) {
        return Lotto.purchaseLottos(purchaseCount);
    }

    // 로또 당첨번호 생성
    public Jackpot createWinningNumbers(String winningNumbers, String bonusNumber) {
        return new Jackpot(winningNumbers, bonusNumber);
    }

    // 로또 결과 객체 생성
    public Result createResult(Jackpot jackpot, List<Lotto> lottos, Purchase purchase) {
        return new Result(jackpot, lottos, purchase);
    }

    // 로또 당첨 계산
    public void calculateWinningRanks(Result result) {
        result.calculateWinningRanks();
    }
    
}
