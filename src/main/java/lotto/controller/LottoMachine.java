package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoBuyer;
import lotto.domain.Rank;
import lotto.input.PaymentInputHandler;
import lotto.input.TargetNumberHandler;
import lotto.service.Calculator;
import lotto.service.LottoNumberGenerator;
import lotto.service.LottoShop;
import lotto.view.LottoView;

public class LottoMachine {
    LottoView lottoView = new LottoView();
    PaymentInputHandler paymentInputHandler = new PaymentInputHandler();
    TargetNumberHandler targetNumberHandler = new TargetNumberHandler();
    LottoShop lottoShop = new LottoShop(new LottoNumberGenerator());

    public void run() {
        LottoBuyer buyer = buyLotto();
        Map<Rank, Integer> result = matchLotto(buyer);
        calculateReward(result);
    }

    private LottoBuyer buyLotto() {
        int amount = paymentInputHandler.divideRequestPaymentIntoLottoPrice();
        List<Lotto> lottoTickets = lottoShop.sell(amount);
        lottoView.printLotto(lottoTickets);

        return new LottoBuyer(lottoTickets);
    }

    private Map<Rank, Integer> matchLotto(LottoBuyer buyer) {
        Lotto target = targetNumberHandler.setTargetLottoByInput();
        int bonus = targetNumberHandler.setBonusByInput(target);
        Console.close();

        return buyer.checkAllLotto(target, bonus);
    }

    private void calculateReward(Map<Rank, Integer> result) {
        double rateOfReturn = Calculator.calculateRateOfReturn(result);
        lottoView.printResult(result, rateOfReturn);
    }
}
