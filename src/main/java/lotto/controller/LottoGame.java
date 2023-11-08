package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.util.ConvertingUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    public void play() {
        String userPrice = InputView.requestPrice();

        Lottos randomLottos = buyLotto(userPrice);

        String userNumbers = InputView.requestNumbers();
        String userBonus = InputView.requestBonus();

        Buyer buyer = guessLotto(userNumbers, userBonus);

        announceResult(
                progress(randomLottos, buyer)
        );
    }

    private Lottos buyLotto(String userPrice) {
        Integer ticketCount = convertToTicket(userPrice);

        OutputView.noticeTicketCount(ticketCount);

        Lottos randomLottos = Lottos.create(ticketCount);

        OutputView.printResult(
                randomLottos.printAllRandomLottos()
        );

        return randomLottos;
    }

    private Buyer guessLotto(String userNumbers, String userBonus) {
        Lotto userLotto = Lotto.create(userNumbers);

        Integer bonus = ConvertingUtil.convertToInteger(userBonus);

        Buyer buyer = Buyer.create(userLotto, bonus);

        return buyer;
    }

    private Result progress(Lottos randomLottos, Buyer buyer) {
        Result result = randomLottos.calcuateResult(buyer);

        return result;
    }

    private void announceResult(Result result) {
        OutputView.noticeHeader();
        OutputView.printResult(
                result.calculateTotalRankStatus()
        );
        OutputView.printTotalRate(result.calculateTotalRate());
    }

    private Integer convertToTicket(String userInput) {
        return ConvertingUtil.convertToTicket(userInput);
    }
}
