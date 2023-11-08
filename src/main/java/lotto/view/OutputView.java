package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Policy;
import lotto.domain.Result;

public class OutputView {
    private static final String BUDGET_INPUT_DESC = "구입금액을 입력해 주세요.";
    private static final String ISSUED_LOTTO_DESC = "개를 구매했습니다.";
    private static final String ANSWER_INPUT_DESC = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_INPUT_DESC = "\n보너스 번호를 입력해 주세요.";
    private static final String RESULT_DESC = "\n당첨 통계\n---";

    public static void printBudgetInputDescription() {
        System.out.println(BUDGET_INPUT_DESC);
    }

    public static void printLottoStatusDescription(int amount) {
        System.out.printf("%n%d%s%n", amount, ISSUED_LOTTO_DESC);
    }

    public static void printLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }

    public static void printAnswerInputDescription() {
        System.out.println(ANSWER_INPUT_DESC);
    }

    public static void printBounsInputDescription() {
        System.out.println(BONUS_INPUT_DESC);
    }

    public static void printResultDescription() {
        System.out.println(RESULT_DESC);
    }

    public static void printRank(Result result) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        for (Policy rank : result.getResults().keySet()) {
            if (rank == Policy.FAIL) {
                continue;
            }
            String str = rank.getMessage()
                    + String.format("(%s원)", formatter.format(rank.getMoney()))
                    + String.format(" - %s개", result.getResults().get(rank));
            System.out.println(str);
        }
    }

    public static void printProfitRate(double profitRate) {
        DecimalFormat formatter = new DecimalFormat("###,###.0");
        System.out.printf("총 수익률은 %s%%입니다.%n", formatter.format(profitRate));
    }
}
