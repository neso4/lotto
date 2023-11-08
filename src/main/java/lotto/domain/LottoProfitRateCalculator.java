package lotto.domain;

import java.util.Map;

public class LottoProfitRateCalculator {
    private static final int PERCENT = 100;

    public LottoProfitRate calculate(Map<LottoGrade, Integer> statistics) {
        if (isEmpty(statistics)) {
            return new LottoProfitRate(0);
        }

        long totalWinningMoney = getTotalWinningMoney(statistics);
        int lottoBoughtPrice = getLottoBoughtPrice(statistics);

        return new LottoProfitRate(
                getProfitRate(totalWinningMoney, lottoBoughtPrice)
        );
    }

    private boolean isEmpty(Map<LottoGrade, Integer> statistics) {
        if (statistics.isEmpty()) {
            return true;
        }

        return statistics.keySet().stream()
                .mapToInt(statistics::get)
                .sum() == 0;
    }

    private long getTotalWinningMoney(Map<LottoGrade, Integer> statistics) {
        return statistics.keySet()
                .stream()
                .mapToLong(lottoGrade -> (long) lottoGrade.getWinningMoney() * statistics.get(lottoGrade))
                .sum();
    }

    private int getLottoBoughtPrice(Map<LottoGrade, Integer> statistics) {
        return statistics.keySet()
                .stream()
                .mapToInt(lottoGrade -> Lotto.LOTTO_PRICE * statistics.get(lottoGrade))
                .sum();
    }

    private double getProfitRate(long totalWinningMoney, int lottoBoughtPrice) {
        return (double) totalWinningMoney / lottoBoughtPrice * PERCENT;
    }
}
