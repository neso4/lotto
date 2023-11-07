package lotto.view;

import lotto.configuration.InputMessage;
import lotto.configuration.WinningLevel;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private final static String DASH = "-";
    private final static String STATISTICS = "당첨 통계";
    private final static String PURCHASE_COUNT = "개를 구매했습니다.";

    public static void OutputViewPurchaseCount(long count) {
        System.out.println(count + PURCHASE_COUNT);
    }

    public static void inputViewPurchaseAmount() {
        System.out.println(InputMessage.INPUT_PURCHASE_AMOUNT.inputMessage());
    }

    public static void lottoNumbersResult(List<Integer> lotto) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Integer number : lotto) {
            joiner.add(number.toString());
        }
        System.out.println(joiner.toString());
    }

    public static void inputViewWinningNumber() {
        System.out.println();
        System.out.println(InputMessage.INPUT_WINNING_NUMBERS.inputMessage());
    }

    public static void inputViewBonusNumber() {
        System.out.println();
        System.out.println(InputMessage.INPUT_BONUS_NUMBER.inputMessage());

    }

    public static void outputViewStatistics(HashMap<String, Long> scoreBoard) {
        System.out.println();
        System.out.println(STATISTICS);
        System.out.println(DASH.repeat(3));
        System.out.println(WinningLevel.FIFTH_PLACE.getResult() + " - " + scoreBoard.get("5th") / WinningLevel.FIFTH_PLACE.getReward() + "개");
        System.out.println(WinningLevel.FOURTH_PLACE.getResult() + " - " + scoreBoard.get("4th") / WinningLevel.FOURTH_PLACE.getReward() + "개");
        System.out.println(WinningLevel.THIRD_PLACE.getResult() + " - " + scoreBoard.get("3rd") / WinningLevel.THIRD_PLACE.getReward() + "개");
        System.out.println(WinningLevel.SECOND_PLACE.getResult() + " - " + scoreBoard.get("2nd") / WinningLevel.SECOND_PLACE.getReward() + "개");
        System.out.println(WinningLevel.FIRST_PLACE.getResult() + " - " + scoreBoard.get("1st") / WinningLevel.FIRST_PLACE.getReward() + "개");
    }

    public static void outputViewTotalReturn(Long totalReturn, Long money) {
        double total = (double) totalReturn / (double) money;
        double roundedTotal = Math.round(total * 10000.0) / 100.0; // 소수점 둘째 자리에서 반올림
        String result = String.format("총 수익률은 %.1f%%입니다.", roundedTotal);
        System.out.println(result);
    }
}
