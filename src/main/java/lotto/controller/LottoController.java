package lotto.controller;

import static lotto.constants.LottoConstant.LOTTO_PRICE_UNIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Statistics;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money money = initMoney();
        List<Lotto> lottoTickets = initLottoTickets(money);
        OutputView.printLottoTickets(lottoTickets);
        WinningNumber winningNumber = initWinningNumber();
        Statistics statistics = initStatistics(lottoTickets, winningNumber);
        OutputView.printStatistics(statistics, money);
    }

    private Money initMoney() {
        try {
            return new Money(InputView.getMoney());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
    }

    private List<Lotto> initLottoTickets(Money money) {
        List<Lotto> lottoTickets = new ArrayList<>();
        int lottoTicketCount = (int) money.getLottoTicketCount(LOTTO_PRICE_UNIT);
        for (int count = 0; count < lottoTicketCount; count++) {
            lottoTickets.add(Lotto.create());
        }
        return lottoTickets;
    }

    private WinningNumber initWinningNumber() {
        Lotto winningLotto = initWinningLotto();
        int bonusNumber = initBonusNumber();
        try {
            return new WinningNumber(winningLotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initWinningNumber();
        }
    }

    private Lotto initWinningLotto() {
        try {
            return new Lotto(InputView.getWinningNumber());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initWinningLotto();
        }
    }

    private int initBonusNumber() {
        try {
            return new LottoNumber(InputView.getBonusNumber()).getNumber();
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initBonusNumber();
        }
    }

    private Statistics initStatistics(List<Lotto> lottoTickets, WinningNumber winningNumber) {
        Map<Rank, Integer> winningResult = Statistics.calculateWinningResult(
                lottoTickets, winningNumber);
        Statistics statistics = new Statistics(winningResult);
        return statistics;
    }
}
