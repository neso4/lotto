package lotto.io.processor;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.statics.WinningGrade;

public class OutputProcessor {

    private static final String PURCHASE_PRICE_HINT = "구입 금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_HINT = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_HINT = "\n보너스 번호를 입력해 주세요.";
    private static final String WINNING_STATICS_HINT = "\n당첨 통계\n---";

    private static final String LOTTO_COUNT_FORMAT = "\n%d개를 구매했습니다.";
    private static final String WINNING_STATICS_FORMAT = "%d개 일치 (%s원) - %d개";
    private static final String WINNING_BONUS_STATICS_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";

    public void outputErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }

    public void printPurchasePriceHint() {
        System.out.println(PURCHASE_PRICE_HINT);
    }

    public void outputWinningNumbersHint() {
        System.out.println(WINNING_NUMBERS_HINT);
    }

    public void outputBonusNumberHint() {
        System.out.println(BONUS_NUMBER_HINT);
    }

    public void outputStaticsHint() {
        System.out.println(WINNING_STATICS_HINT);
    }

    public void outputLottoCount(final int count) {
        final String message = String.format(LOTTO_COUNT_FORMAT, count);
        System.out.println(message);
    }

    public void outputWinningStatics(final WinningGrade winningGrade, final int count) {
        final DecimalFormat decimalFormat = new DecimalFormat("#,###");

        String message = String.format(WINNING_STATICS_FORMAT,
                winningGrade.getMatchedCount(),
                decimalFormat.format(winningGrade.getWinningPrice()),
                count);

        if (winningGrade.isMatchedBonusNumber()) {
            message = String.format(WINNING_BONUS_STATICS_FORMAT,
                    winningGrade.getMatchedCount(),
                    decimalFormat.format(winningGrade.getWinningPrice()),
                    count);
        }

        System.out.println(message);
    }

    public void outputProfitRate(final float profitRate) {
        final String message = String.format(PROFIT_RATE_FORMAT, profitRate);
        System.out.println(message);
    }

    public void outputLottoNumbers(final List<Integer> numbers) {
        System.out.println(numbers);
    }
}
