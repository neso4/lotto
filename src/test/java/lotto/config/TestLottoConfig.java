package lotto.config;

import lotto.TestConstant;
import lotto.controller.LottoController;
import lotto.random.RandomNumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class TestLottoConfig implements LottoConfig {
    @Override
    public RandomNumberGenerator randomNumberGenerator() {
        return (min, max, count) -> TestConstant.normalNumbers;
    }

    @Override
    public LottoService lottoService() {
        return new LottoService(randomNumberGenerator());
    }

    @Override
    public InputView inputView() {
        return new InputView();
    }

    @Override
    public OutputView outputView() {
        return new OutputView();
    }

    @Override
    public LottoController lottoController() {
        return new LottoController(lottoService(), inputView(), outputView());
    }
}
