package lotto;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoOption;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ResultSheet;
import lotto.domain.WinningLotto;
import lotto.message.ViewMessage;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MainController {
    public static void run() {
        Lottos purchasedLotto = initLottos();
        OutputView.printLottos(purchasedLotto);
        WinningLotto winningLotto = initAnswerLotto();
        ResultSheet resultSheet = winningLotto.calculateResult(purchasedLotto);
        OutputView.printResult(resultSheet);
    }

    private static Lottos initLottos() {
        try {
            Money money = new Money(InputView.readInteger(ViewMessage.INPUT_PURCHASE_MONEY));
            return Lottos.purchaseLottos(money);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return initLottos();
        }
    }

    private static WinningLotto initAnswerLotto() {
        Lotto answerLotto = initLotto();
        BonusNumber bonus = initBonus();
        return new WinningLotto(answerLotto, bonus);
    }

    private static Lotto initLotto() {
        try {
            String lottoNumberString = InputView.readString(ViewMessage.INPUT_ANSWER_LOTTO);
            List<Integer> lottoNumbers = Utils.convertStringToIntegerList(lottoNumberString,
                    LottoOption.LOTTO_NUMBER_DELIMITER);
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return initLotto();
        }
    }

    private static BonusNumber initBonus() {
        try {
            int bonus = InputView.readInteger(ViewMessage.INPUT_BONUS_NUMBER);
            return new BonusNumber(bonus);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return initBonus();
        }
    }
}
