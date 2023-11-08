package lotto;

import java.util.List;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<Lotto> getLotto() {
        int price = 0;

        outputView.displayPriceQuestion();
        price = inputView.inputPrice();
        return lottoService.createLotto(price);
    }

    public void printLotto(List<Lotto> lottos) {
        outputView.printLotto(lottos);
    }

    public List<Integer> getWinNumber() {
        outputView.displayWinNumberQuestion();
        return inputView.inputWinNumbers();
    }

    public int getBonusNumber(List<Integer> winNumber) {
        outputView.displayBonusNumberQuestion();
        return inputView.inputBonusNumber(winNumber);
    }

    public Prize[] compareLottoNumbers(List<Lotto> lottos, List<Integer> winNumber, int bonusNumber) {
        return lottoService.compare(lottos, winNumber, bonusNumber);
    }

    public double benefitPercent(Prize[] prizes, int numberOfLotto) {
        return lottoService.getStatistics(prizes, numberOfLotto);
    }
}
