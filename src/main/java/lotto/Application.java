package lotto;

import lotto.controller.Game;

public class Application {
    public static void main(String[] args) {
        Game lottoGame = new Game();
        lottoGame.start();
    }
}
