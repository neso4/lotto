package lotto;

import lotto.controller.BonusController;
import lotto.controller.LottoController;
import lotto.controller.LottosController;
import lotto.domain.*;
import lotto.controller.MoneyController;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.configuration.Constants.*;

public class GameManager {
    private final InputView inputView;
    private final OutputView outputView;

    private final NumberGenerator numberGenerator;

    private final MoneyController moneyController;
    private final LottosController lottosController;
    private final LottoController lottoController;
    private final BonusController bonusController;


    public GameManager(InputView inputView,
                       OutputView outputView,
                       NumberGenerator numberGenerator,
                       MoneyController moneyController,
                       LottosController lottosController,
                       LottoController lottoController,
                       BonusController bonusController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
        this.moneyController = moneyController;
        this.lottosController = lottosController;
        this.lottoController = lottoController;
        this.bonusController = bonusController;
    }

    public void play() {
        Money money = getMoney();
        Lottos lottos = getLottos(money.getLottoCount());
        Lotto winningLotto = getWinningLotto();
        Bonus bonus = getBonus(winningLotto);
    }

    private Money getMoney() {
        while (true) {
            try {
                outputView.println(Message.NEED_PURCHASE_MONEY);
                String input = inputView.readOne();
                int money = numberGenerator.createOne(input);
                return moneyController.create(money);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lottos getLottos(int lottoCount) {
        List<List<Integer>> randomNumberLists = numberGenerator.createRandomNumberLists(lottoCount);
        Lottos lottos = lottosController.create(randomNumberLists);
        outputView.println(lottos);
        return lottos;
    }

    private Lotto getWinningLotto() {
        Lotto winningLotto;
        while (true) {
            try {
                outputView.println(Message.ENTER_WINNING_LOTTO);
                String input = inputView.readOne();
                List<Integer> numbers = numberGenerator.createNumbers(input);
                winningLotto = lottoController.create(numbers);
                return winningLotto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Bonus getBonus(Lotto winningLotto) {
        while (true) {
            try {
                outputView.println(Message.NEED_BONUS_NUMBER);
                String input = inputView.readOne();
                int bonusNumber = numberGenerator.createOne(input);
                return bonusController.create(bonusNumber, winningLotto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
