package lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    private static final String PURCHASE_QUANTITY_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계";
    private static final String DIVIDING_LINE = "---";
    private static final String COUNT_UNIT = "개";

    private static final String PROFIT_RATIO_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printPurchaseQuantity(int purchaseQuantity) {
        System.out.println();
        System.out.println(purchaseQuantity + PURCHASE_QUANTITY_MESSAGE);
    }

    public void printPurchasedLottoNumbers(List<List<Integer>> lottoNumbers) {
        for (List<Integer> lottoNumber : lottoNumbers) {
            System.out.println(lottoNumber);
        }
    }

    public void printWinningStatistics(EnumMap<LottoRank, Integer> lottoRakingMap) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVIDING_LINE);

        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.LAST_PLACE)
                .forEach(rank ->
                        System.out.println(rank.getOutputMessage()
                                + lottoRakingMap.get(rank) + COUNT_UNIT));
    }

    public void printProfitRatio(double profit) {
        System.out.printf(PROFIT_RATIO_MESSAGE, profit);
    }
}
