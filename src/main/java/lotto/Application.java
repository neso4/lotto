package lotto;

import lotto.controller.GameController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameController gameController = new GameController();
        try {
            gameController.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
