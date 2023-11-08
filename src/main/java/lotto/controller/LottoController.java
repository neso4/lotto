package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGameResult;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void launch() {
        buyLottos();

        Lotto winningLotto = inputView.inputWinningLotto();
        int bonusNumber = inputView.inputBonusNumber();

        LottoGameResult lottoGameResult = lottoService.getResultOfLottos(winningLotto, bonusNumber);
        outputView.printLottoResult(lottoGameResult);
    }

    private void buyLottos() {
        int money = inputView.inputPurchaseMoney();

        List<Lotto> userLottos = lottoService.buyLotto(money);
        outputView.printPurchasedLottos(userLottos);
    }
}
