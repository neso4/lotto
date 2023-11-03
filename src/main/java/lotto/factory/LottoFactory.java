package lotto.factory;

import lotto.controller.LottoController;
import lotto.io.InputManager;
import lotto.io.InputMapper;
import lotto.io.InputView;
import lotto.io.OutputView;
import lotto.validator.InputValidator;

public class LottoFactory {

    public LottoController lottoController() {
        return new LottoController(inputManager(), outputView());
    }

    private InputManager inputManager() {
        return new InputManager(inputView(), inputMapper());
    }

    private InputView inputView() {
        return new InputView(inputValidator());
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    private InputMapper inputMapper() {
        return new InputMapper();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
