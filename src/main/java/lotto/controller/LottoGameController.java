package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoBuyer;
import lotto.model.LottoSeller;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.service.LottoGameService;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoGameController {
    private final ConsoleInputView inputView;
    private final ConsoleOutputView outputView;
    private final LottoGameService lottoGameService;

    public LottoGameController(
            ConsoleInputView inputView,
            ConsoleOutputView outputView,
            LottoGameService lottoGameService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGameService = lottoGameService;
    }

    public void run() {
        LottoSeller seller = lottoGameService.generateSeller();
        LottoBuyer buyer = lottoGameService.generateBuyer();
        int countOfLotto = lottoGameService.calculateLottoCountOnBuy(buyer);

        Lottos lottos = lottoGameService.generateLottosOnSell(seller, countOfLotto);
        lottoGameService.printLottoNumbersByCount(lottos);

        WinningLotto winningLotto = lottoGameService.getWinningLotto();
    }
}
