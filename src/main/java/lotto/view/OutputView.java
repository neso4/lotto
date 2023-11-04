package lotto.view;

import java.util.List;
import lotto.lotto.Lotto;
import lotto.money.Money;

public class OutputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String BUY_LOTTOS = "개를 구매했습니다.";
    public static final String INPUT_WINNING_LOTTO_MESSAGE = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    public static void inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public static void buyLottos(Money money, List<Lotto> lottos) {
        System.out.println();
        System.out.println(money.getLottoCount() + BUY_LOTTOS);
        lottos.forEach(System.out::println);
    }

    public static void inputWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
    }

    public static void inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
    }
}
