package lotto;

import lotto.domain.LottoMachine;
import lotto.domain.lotto.LottosRepository;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame(
                new InputView(),
                new OutputView(),
                new LottoMachine(new LottosRepository())
        );
        lottoGame.startGame();
    }
}
