package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.Money;
import lotto.model.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    NumberGenerator numberGenerator = new NumberGenerator();
    // private int ticketCount = 0;

    public void start() {
        Money money = getValidMoneyInput();
        int ticketCount = getTicketCount(money);
        OutputView.printTicketCount(ticketCount);
        List<Lotto> userLottos = createLotto(ticketCount);
        OutputView.printUserLottos(userLottos);
    }

    private Money getValidMoneyInput() {
        Money money = null;
        while (money == null) {
            try {
                money = new Money(InputView.getMoneyInput());
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
            }
        }
        return money;
    }

    private int getTicketCount(Money money) {
        int ticketCount = money.calculateTicketCount();
        return ticketCount;
    }

    private List<Lotto> createLotto(int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = new Lotto(numberGenerator.createRandomLottoNumbers());
            lottos.add(lotto);
        }
        return lottos;
    }
}