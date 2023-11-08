package lotto.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import lotto.domain.BonusNumber;
import lotto.domain.DrawingResults;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.ProfitRate;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoMachine {

    public Lottos issuedLottos(final PurchaseAmount purchaseAmount) {
        return new Lottos(purchaseAmount);
    }


    public DrawingResults draw(final Lottos lottos, final WinningLotto winningLotto,
                               final BonusNumber bonusNumber) {
        List<Lotto> lottosContents = lottos.getLottos();

        DrawingResults drawingResults = new DrawingResults();

        calculateDrawingResults(winningLotto, bonusNumber, lottosContents, drawingResults);

        return drawingResults;
    }

    private void calculateDrawingResults(WinningLotto winningLotto, BonusNumber bonusNumber, List<Lotto> lottosContents,
                                         DrawingResults drawingResults) {
        for (Lotto lotto : lottosContents) {
            int matchedWinningLottoCount = lotto.matchWinningLottoCount(winningLotto);
            boolean hasBonusNumber = lotto.hasBonusNumber(bonusNumber);

            Rank calculateResult = Rank.calculate(matchedWinningLottoCount, hasBonusNumber);
            drawingResults.count(calculateResult);
        }
    }

    public ProfitRate calculateProfitRate(final Lottos lottos, final DrawingResults drawingResult) {
        Set<Entry<Rank, Integer>> results = drawingResult.getResults().entrySet();
        long totalRevenue = 0;

        for (Entry<Rank, Integer> result : results) {
            totalRevenue += (long) result.getKey().getWinningAmount() * result.getValue();
        }

        return new ProfitRate(getProfitRate(lottos, totalRevenue));
    }

    private double getProfitRate(Lottos lottos, double totalRevenue) {
        long totalCost = (long) PurchaseAmount.PURCHASE_AMOUNT_UNIT * lottos.getLottos().size();
        double profitRate = totalRevenue / totalCost * 100;

        return profitRate;
    }
}
