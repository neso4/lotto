package lotto.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import lotto.domain.LottoBundle;
import lotto.domain.LottoOrder;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String RESULT_MSG = "당첨 통계" + System.lineSeparator() + "---";
    private static final String RESULT_FORMAT = "%d개 일치%s(%s원) - %s개" + System.lineSeparator();
    private static final String BUY_MSG_SUFFIX = "개를 구매했습니다.";
    private static final String CORRECT_BONUS_MSG = ", 보너스 볼 일치 ";
    private static final String TOTAL_PROFIT_RATE_MSG = "총 수익률은 %s%%입니다.";
    private static final String SPACE = " ";
    private static final String DECIMAL_FORMAT_PATTERN = "#,##0.0";

    private OutputView() {
    }

    public static void printBuyQuantity(final LottoOrder lottoOrder, final int price) {
        System.out.println(lottoOrder.getOrderQuantity(price) + BUY_MSG_SUFFIX);
    }

    public static void printBuyLotto(final LottoBundle lottoBundle) {
        System.out.println(lottoBundle.toString() + System.lineSeparator());
    }

    public static void printResultMessage() {
        System.out.println(RESULT_MSG);
    }

    public static void printLottoResult(final LottoResult lottoResult) {
        for (Rank rank : Rank.filterGetPrize()) {
            String formattedPrize = formatNumber(rank.getPrize());
            String formattedCount = formatNumber(lottoResult.countRank(rank));
            System.out.printf(RESULT_FORMAT, rank.getMatchCount(), correctBonus(rank), formattedPrize, formattedCount);
        }
    }

    private static String correctBonus(final Rank rank) {
        if (rank.equals(Rank.SECOND_PRIZE)) {
            return CORRECT_BONUS_MSG;
        }
        return SPACE;
    }

    public static void printProfitRate(final float rate) {
        String formattedRate = formatDecimal(rate);
        System.out.printf(TOTAL_PROFIT_RATE_MSG, formattedRate);
    }

    public static void printErrorMessage(final IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage() + System.lineSeparator());
    }

    private static String formatNumber(final int number) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(number);
    }

    private static String formatDecimal(final float rate) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
        return decimalFormat.format(rate);
    }
}
