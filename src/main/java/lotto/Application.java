package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Game game = new Game();
        game.renderMoneyView();
        game.renderLotto();
        game.renderWinningLotto();
        game.renderBonus();
        game.matchLotto();
    }
}

