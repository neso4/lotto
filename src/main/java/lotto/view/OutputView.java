package lotto.view;

import static lotto.util.Constants.LINE;

import lotto.model.PurchaseAmount;

public class OutputView {
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String SEPARATOR = "---";
    private static final String PROFIT_RATE = "총 수익률은 %.1f%%입니다.";
    private static final String PURCHASE = "%d개를 구매했습니다.";
    private static final String COUNT = "개";
    private static final String HYPHEN = " - ";

    public static void getPurchase(final PurchaseAmount amount) {
        System.out.println(LINE + formatPurchase(amount));
    }

    public static void getLottoNumbers(String lotto) {
        System.out.println(lotto);
    }

    private static String formatPurchase(PurchaseAmount amount) {
        return String.format(PURCHASE, amount.exchangeAmount());
    }

    public static void winningStatistics() {
        System.out.println(LINE + WINNING_STATISTICS);
    }

    public static void separator() {
        System.out.println(SEPARATOR);
    }

    public static void getResult(final String key, final Integer value) {
        System.out.println(key + HYPHEN + value + COUNT);
    }

    public static void getProfit(final double profit) {
        System.out.printf((PROFIT_RATE), profit);
    }
}
