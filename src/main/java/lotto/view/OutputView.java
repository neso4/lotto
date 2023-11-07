package lotto.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import lotto.constant.message.OutputMessage;
import lotto.domain.Lotto;

import java.util.List;
import lotto.constant.Rank;

public class OutputView {

    public void printRequestPurchaseAmount() {
        System.out.println(OutputMessage.REQUEST_PURCHASE_AMOUNT.getMessage());
    }

    public void printRequestWinningNumbers() {
        System.out.println(OutputMessage.REQUEST_WINNING_NUMBERS.getMessageWithNewLine());
    }

    public void printRequestBonusNumber() {
        System.out.println(OutputMessage.REQUEST_BONUS_NUMBER.getMessageWithNewLine());
    }

    public void printPurchasedLottoQuantity(int lottoQuantity) {
        System.out.println(OutputMessage.PURCHASED_LOTTO_QUANTITY.getMessageWithNewLine(lottoQuantity));
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(OutputMessage.LOTTO_NUMBERS.format(lotto.getNumbers()));
        }
    }

    public void printResults(Map<Rank, Integer> results) {
        printResultMessage();
        NumberFormat numberFormat = getNumberInstance();
        for (Rank rank : Rank.values()) {
            printResult(results, rank, numberFormat);
        }
    }

    private static void printResultMessage() {
        System.out.println(OutputMessage.RESULTS_HEADER.getMessageWithNewLine());
        System.out.println(OutputMessage.RESULTS_SEPARATOR.getMessage());
    }

    private void printResult(Map<Rank, Integer> results, Rank rank, NumberFormat numberFormat) {
        if (rank == Rank.FAIL) {
            return;
        }
        String prizeMoneyFormatted = numberFormat.format(rank.getWinningMoney());
        if (rank == Rank.SECOND) {
            System.out.println(OutputMessage.RESULT_SECOND.format(
                    prizeMoneyFormatted, results.getOrDefault(rank, 0)));
            return;
        }
        System.out.println(OutputMessage.RESULT_LINE.format(
                rank.getMatchCount(), prizeMoneyFormatted, results.getOrDefault(rank, 0)));
    }

    private NumberFormat getNumberInstance() {
        return NumberFormat.getNumberInstance(Locale.US);
    }

    public void printProfitRate(double profitRate) {
        DecimalFormat profitFormat = new DecimalFormat("#.#");
        System.out.println(OutputMessage.PROFIT_RATE.format(profitFormat.format(profitRate)));
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
