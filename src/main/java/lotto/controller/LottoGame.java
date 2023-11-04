package lotto.controller;

import java.util.Map;
import lotto.common.LottoRank;
import lotto.domain.Lotto;
import lotto.dto.LottoGameResponse;
import lotto.service.LottoGameService;
import lotto.domain.Money;
import lotto.view.InputOutputView;

public class LottoGame {
    private LottoGameService lottoGameService;
    private InputOutputView inputOutputView;

    public LottoGame() {
        this.lottoGameService = new LottoGameService();
        this.inputOutputView = new InputOutputView();
    }

    public void start() throws IllegalArgumentException {
        Money money = inputOutputView.inputMoney();
        LottoGameResponse lottoGameResponse = lottoGameService.buy(money);
        inputOutputView.printBuyLottos(lottoGameResponse);

        Lotto winningNumbers = inputOutputView.inputWinningNumbers();
        int bonusNumber = inputOutputView.inputBonusNumber();

        Map<LottoRank, Integer> result = lottoGameService.calculateResult(winningNumbers, bonusNumber);
        inputOutputView.printResult(result);
    }
}
