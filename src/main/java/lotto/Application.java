package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.gameStarter.LottoGame;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        LottoGame lottoGame = new LottoGame();
        lottoGame.run();
        Console.close();
    }
}
