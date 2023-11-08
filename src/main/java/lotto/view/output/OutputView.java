package lotto.view.output;

import lotto.model.dto.LottoResponse;
import lotto.model.dto.PrizeResult;
import java.util.List;

public interface OutputView {

    void askInvestMoney();
    void printBoughtLottoSize(final int size);
    void printEachLottoNumbers(final List<LottoResponse> lottoResponses);
    void askGoalNumbers();
    void askBonusNumber();
    void alertResult();
    void printEachPrize(final List<PrizeResult> results);
    void printProfitRate(final double rate);
    void printExceptionMessage(final String message);
}
