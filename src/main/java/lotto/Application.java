package lotto;

import lotto.constant.ExceptionConstant;
import lotto.controller.GameMainController;
import lotto.service.GameService;
import lotto.util.RandomNumberGenerator;
import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
            GameMainController gameMainController = new GameMainController
                    (new GameService(), new InputView(new InputValidator()), new OutputView());
            gameMainController.run();
    }
}
