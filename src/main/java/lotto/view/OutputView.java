package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.controller.dto.LottoResponse;

public interface OutputView {

    void printMoneyRequestMessage();

    void printPurchasedLottos(List<LottoResponse> lottos);

    void printWinningNumberRequestMessage();

    void printBonusNumberRequestMessage();

    void printWinningStatistics(Map<String, Long> winningStatistics);

    void printTotalProfit(double totalProfit);

    void printErrorMessage(String message);
}
