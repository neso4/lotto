package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.config.ApplicationConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = ApplicationConfig.controller;
        lottoController.run();
        Console.close();
    }
}
