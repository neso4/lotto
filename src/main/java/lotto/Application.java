package lotto;

import lotto.domain.game.Game;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();

        game.run();
    }
}
