package lotto;

import java.util.List;
import lotto.controller.LottoInputController;
import lotto.domain.LottoService;
import lotto.domain.LottoStatistics;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoInputController inputController = LottoInputController.getInstance();
        LottoService lottoService = LottoService.getInstance();

        final int amount = inputController.getUserInputForAmount();
        final List<Lotto> lottos = lottoService.purchaseLottos(amount);
        OutputView.printLottos(lottos.size(), lottos);

        final List<Integer> winningNumbers = inputController.getUserInputForWinningNumbers();
        final int bonusNumber = inputController.getUserInputForBonusNumber(winningNumbers);

        LottoStatistics statistics = new LottoStatistics(lottos, winningNumbers, bonusNumber);
        OutputView.presentResults(statistics.getWinsPerCategory(), statistics.getTotalRate());
    }
}
