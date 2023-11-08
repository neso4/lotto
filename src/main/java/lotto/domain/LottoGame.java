package lotto.domain;

import lotto.domain.lotto.LottoAmount;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.result.Result;
import lotto.domain.result.Results;
import lotto.dto.ResultDto;

import java.util.List;
import java.util.Map;

public class LottoGame {

    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private final LottoAmount purchasePrice;

    public LottoGame(Lottos lottos, WinningLotto winningLotto, LottoAmount purchasePrice) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.purchasePrice = purchasePrice;
    }

    public ResultDto getResult() {
        Results results = this.generateResults();

        double profit = results.getProfit(purchasePrice.getAmount());
        Map<Result, Integer> statistics = results.getStatistics();
        statistics.remove(Result.MISS);

        return new ResultDto(profit, statistics);
    }

    private Results generateResults() {
        List<Result> results = lottos.getResults(winningLotto);
        return new Results(results);
    }
}
