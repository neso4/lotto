package lotto.view;

import java.math.BigDecimal;
import java.util.Arrays;
import lotto.model.LottoTicket;
import lotto.model.Rank;
import lotto.model.ResultDetails;

public class OutputView {

    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String PURCHASE_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String WINNING_STATISTICS_HEADER = "당첨 통계";
    private static final String HORIZONTAL_LINE = "---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printPurchaseAmountMessage() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
    }

    public void printNumberOfLotto(final int numberOfLotto) {
        printEmptyLine();
        System.out.printf(PURCHASE_MESSAGE, numberOfLotto);
    }

    public void printLottoTicket(final LottoTicket lottoTicket) {
        lottoTicket.getLottos()
                .forEach(System.out::println);
    }

    public void printWinningNumbersMessage() {
        printEmptyLine();
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
    }

    public void printBonusNumberMessage() {
        printEmptyLine();
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
    }

    public void printWinningStatisticsHeader() {
        printEmptyLine();
        System.out.println(WINNING_STATISTICS_HEADER);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printWinningStatistics(final ResultDetails resultDetails) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> System.out.printf(rank.getMessage(), resultDetails.getWinnerCountByRank(rank)));
    }

    public void printExceptionMessage(final Exception exception) {
        printEmptyLine();
        System.out.println(exception.getMessage());
    }

    public void printProfitRate(final BigDecimal profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
