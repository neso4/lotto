package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.dto.LottoInfos;
import lotto.service.LottoCreationService;
import lotto.view.CreatedLottosView;
import lotto.view.InputView;

public class LottoController {
    private LottoCreationService lottoCreationService;

    public LottoController() {
        this.lottoCreationService = new LottoCreationService(new NumberPickingStrategyImpl());
    }

    public void run() {
        CreatedLottosView.viewCreatedLottos(createLottos());
    }

    private LottoInfos createLottos() {
        return (LottoInfos) repeatUntilNoInternalException(
                () -> lottoCreationService.createLottos(InputView.readMoneyInput())
        );
    }

    private Object repeatUntilNoInternalException(Supplier<Object> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handleException(e);
            }
        }
    }
}
