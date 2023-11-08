package lotto;

import lotto.domain.Game;
import lotto.domain.LottoMachine;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {

        Game game = new Game(new ConsoleInputView(), new ConsoleOutputView(),
                new LottoMachine());
        game.start();
    }
}
