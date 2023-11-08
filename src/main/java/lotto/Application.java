package lotto;

import lotto.controller.GameController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {

        GameController game = new GameController(new InputView(), new OutputView());
        game.play();

    }
}
