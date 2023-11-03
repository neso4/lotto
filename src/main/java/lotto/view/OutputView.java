package lotto.view;

import java.util.List;
import lotto.lotto.Lotto;
import lotto.money.Money;

public class OutputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String BUY_LOTTOS = "개를 구매했습니다.";

    public static void inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public static void buyLottos(Money money, List<Lotto> lottos) {
        System.out.println();
        System.out.println(money.getLottoCount() + BUY_LOTTOS);
        lottos.forEach(System.out::println);
    }
}
