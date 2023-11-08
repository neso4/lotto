package lotto.view;

import lotto.domain.Rank;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String TYPE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String COMMA = ", ";
    private static final String TYPE_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String TYPE_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String DRAW_RESULT = "당첨 통계";
    private static final String BRACKETS = "---";

    public static void printPurchaseAmount() {
        System.out.println(TYPE_PURCHASE_AMOUNT);
    }

    public static void printPurchaseCount(int count) {
        blankSpace();
        System.out.printf("%d개를 구매했습니다." , count);
    }

    public static void printBoughtLottoNumbers(List<Integer> numbers) {
        blankSpace();
        System.out.print(OPEN_BRACKET);
        String result = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(COMMA));
        System.out.print(result);
        System.out.print(CLOSE_BRACKET);
    }

    public static void printWinningNumbers() {
        blankSpace();
        blankSpace();
        System.out.println(TYPE_WINNING_NUMBERS);
    }

    public static void printBonusNumber() {
        blankSpace();
        System.out.println(TYPE_BONUS_NUMBER);
    }

    public static void printStatistics() {
        blankSpace();
        System.out.println(DRAW_RESULT);
        System.out.println(BRACKETS);
    }

    public static void printRankResult(LinkedHashMap<Rank, Integer> map) {
        for(Rank rank : Rank.values()) {
            if (checkFail(rank)) {
                continue;
            }
            System.out.printf(rank.getDrawResult() + System.lineSeparator(), map.getOrDefault(rank, 0));
        }
    }

    public static void printProfitResult(double result) {
        System.out.println("총 수익률은 " + String.format("%.1f", result) + "%입니다.");
    }

    private static boolean checkFail(Rank rank){
        return rank == Rank.FAIL;
    }

    private static void blankSpace() {
        System.out.println();
    }

}
