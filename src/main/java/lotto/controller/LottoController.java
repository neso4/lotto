package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.Ranks;
import lotto.domain.WinningLotto;
import lotto.dto.LottoInfos;
import lotto.service.LottoCreationService;
import lotto.service.WinningLottoRegisterService;
import lotto.view.CreatedLottosView;
import lotto.view.InputView;
import lotto.view.WinningStatusView;

public class LottoController {
    private LottoCreationService lottoCreationService;
    private WinningLottoRegisterService winningLottoRegisterService;

    public LottoController() {
        this.lottoCreationService = new LottoCreationService(new NumberPickingStrategyImpl());
        this.winningLottoRegisterService = new WinningLottoRegisterService();
    }

    public void run() {
        CreatedLottosView.viewCreatedLottos(createLottos());

        registerWinningLottoNumbers();
        registerBonusNumber();

        WinningLotto winningLotto = winningLottoRegisterService.getWinningLotto();
        Ranks ranks = winningLotto.calcRanksOfGivenLottos(lottoCreationService.getLottos());
        WinningStatusView.viewWinningStatus(ranks.getRankCountPairs());

    }

    private WinningLotto registerBonusNumber() {
        return (WinningLotto) repeatUntilNoInternalException(
                () -> winningLottoRegisterService.registerBonusNumber(InputView.readBonusNumberInput()));
    }

    private WinningLotto registerWinningLottoNumbers() {
        return (WinningLotto) repeatUntilNoInternalException(
                () -> winningLottoRegisterService.registerNumbers(InputView.readNumbersInput()));
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
