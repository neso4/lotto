package lotto;

import java.util.ArrayList;
import java.util.List;
import view.InputView;

public class Application {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoney());
        int ticketCount = money.geBuyTicket();
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoList.add(new Lotto(LottoGenerator.generate()));
        }
    }
}
