package lotto;

import lotto.controller.GameController;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameController gameController = new GameController(new InputView());
        gameController.proceedGame();
    }
}
