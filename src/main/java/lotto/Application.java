package lotto;

import lotto.controller.LottoGame;
import lotto.domain.User;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        User user = new User();
        LottoGame lottoGame = new LottoGame(inputView, outputView, user);
        lottoGame.start();
    }
}
