package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoWinner;
import lotto.model.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final Money money;
    private final Set<Lotto> lottos;
    private final LottoWinner lottoWinner;

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    private LottoController() {
        this.money = new Money();
        this.lottos = new HashSet<>();
        this.lottoWinner = new LottoWinner();
    }

    public static LottoController lottoPlayController() {
        return new LottoController();
    }

    public void buyLotto() {
        String input = inputView.showInputMoneyMessage();
        money.inputMoney(input);
        int quantity = money.countQuantity();
        outputView.showQuantityMessage(quantity);
        generateLotto(quantity);
    }

    public void checkLottoResult() {
        lottoWinner.createWinnerLotto();
        lottoWinner.matchWinnerLotto(lottos);
        lottoWinner.showResultBoard();
        int prize = lottoWinner.calculatePrize();
        outputView.showIncomeRate(money.calculateIncomeRate(prize));
    }

    public void generateLotto(int quantityOfLotto) {
        for (int i = 0; i < quantityOfLotto; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
            showGeneratedLottos(numbers);
        }
    }

    private void showGeneratedLottos(List<Integer> LottoNumbers) {
        outputView.showLotto(LottoNumbers.stream().sorted().collect(Collectors.toList()));
    }
}
