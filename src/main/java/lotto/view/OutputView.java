package lotto.view;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.util.enumerator.LottoRank;

public class OutputView {
    private static final String DIGIT_COMMA_WITH_BLANK = ", ";
    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    public void outputLottoNumbers(List<Lotto> lotties) {
        System.out.printf(Message.OUTPUT_COMPLETE_BUY_LOTTO.message, lotties.size());
        for (Lotto lotto : lotties) {
            List<Integer> numbers = lotto.getNumbers();
            System.out.printf(Message.OUTPUT_LOTTO_NUMBERS.message, joinFormatter(numbers));
        }
    }

    private String joinFormatter(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DIGIT_COMMA_WITH_BLANK));
    }

    public void outputLottoResults(LottoResult result) {
        System.out.printf(Message.OUTPUT_LOTTO_RESULTS.message);

        for (int i = LottoRank.values().length - 2; i >= 0; i--) {
            LottoRank rank = LottoRank.values()[i];
            printRankCount(result, rank);
        }
    }

    private void printRankCount(LottoResult result, LottoRank rank) {
        int count = Collections.frequency(result.getRankResults(), rank);
        System.out.printf(rank.getContent()
                + Message.OUTPUT_LOTTO_RESULT_COUNT.message, count);
    }

    public void outputProfitRate(double profitRate) {
        String formattedRate = thousandFormatter(profitRate);
        System.out.printf(Message.OUTPUT_PROFIT_RATE_BY_PERCENT.message, formattedRate);
    }

    private String thousandFormatter(double profitRate) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.#");
        return decimalFormat.format(profitRate);
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    private enum Message {
        OUTPUT_COMPLETE_BUY_LOTTO("\n%s개를 구매했습니다.\n"),
        OUTPUT_LOTTO_NUMBERS("[%s]\n"),
        OUTPUT_LOTTO_RESULTS("\n당첨 통계\n---\n"),
        OUTPUT_LOTTO_RESULT_COUNT(" - %d개\n"),
        OUTPUT_PROFIT_RATE_BY_PERCENT("총 수익률은 %s%%입니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
