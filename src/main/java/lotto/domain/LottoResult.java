package lotto.domain;

import java.util.HashMap;
import java.util.Set;

public class LottoResult {

    private static final int DEFAULT_VALUE = 0;
    private static final int ADD_COUNT = 1;
    private static final int PERCENT = 100;

    private final HashMap<LottoRanking, Integer> results;
    private final PurchaseAmount purchaseAmount;

    public LottoResult(HashMap<LottoRanking, Integer> results, PurchaseAmount purchaseAmount) {
        this.results = results;
        this.purchaseAmount = purchaseAmount;
    }


    public static LottoResult resultCalculation(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber, PurchaseAmount purchaseAmount) {
        HashMap<LottoRanking, Integer> results = new HashMap<>();

        for (Lotto lotto : lottos.getLottos()) {
            LottoRanking ranking = LottoRanking.getLottoRanking(winningNumber.winningNumberCount(lotto), bonusNumber.bonusNumberMatch(lotto));
            results.put(ranking, results.getOrDefault(ranking, DEFAULT_VALUE) + ADD_COUNT);
        }

        return new LottoResult(results, purchaseAmount);
    }

    public Integer getResult(LottoRanking key) {
        return results.getOrDefault(key, DEFAULT_VALUE);
    }

    public double getProfitRate() {
        Set<LottoRanking> lottoRanks = results.keySet();
        double totalPrizeMoney = getTotalPrizeMoney(lottoRanks);
        double purchaseAmonut = purchaseAmount.getAmount();

        return calculationYield(totalPrizeMoney, purchaseAmonut);
    }

    private int getTotalPrizeMoney(Set<LottoRanking> lottoRanks) {
        return (int) lottoRanks.stream()
                .mapToLong(lottoRank -> lottoRank.getTotalPrizeMoney(results.get(lottoRank)))
                .sum();
    }

    private double calculationYield(double totalPrizeMoney, double purchaseAmonut) {
        return (totalPrizeMoney / purchaseAmonut) * PERCENT;
    }
}
