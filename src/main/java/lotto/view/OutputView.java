package lotto.view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String MONEY_REQUEST_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String COUNT_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String ANSWER_REQUEST_MESSAGE = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_REQUEST_MESSAGE = "\n보너스 번호를 입력해 주세요.";
    private static final String RESULT_MESSAGE = "\n당첨 통계\n---";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.1f%%입니다.";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public static void printPurchase() {
        System.out.println(MONEY_REQUEST_MESSAGE);
    }

    public static void printLottoCount(int lottoCount) {
        System.out.println(String.format(COUNT_MESSAGE, lottoCount));
    }

    public static void printLotto(List<Integer> lotto) {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int number : lotto) {
            stringJoiner.add(String.valueOf(number));
        }
        System.out.println(stringJoiner);
    }

    public static void printAnswer() {
        System.out.println(ANSWER_REQUEST_MESSAGE);
    }

    public static void printBonus() {
        System.out.println(BONUS_REQUEST_MESSAGE);
    }

    public static void printPrice(String result, String price, int count) {
        System.out.println(String.format("%s(%s원) - %d개", result, price, count));
    }

    public static void printResult() {
        System.out.println(RESULT_MESSAGE);
    }

    public static void printProfit(Float profit) {
        System.out.println(String.format(PROFIT_MESSAGE, profit));
    }

    public static void printException(String exceptionMessage) {
        System.out.print(ERROR_MESSAGE);
        System.out.println(exceptionMessage);
    }
}
