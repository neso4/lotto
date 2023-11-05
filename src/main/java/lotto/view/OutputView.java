package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.Rank;
import lotto.util.LottoUtil;

public class OutputView {
    private static final String RESULT_HEADER = "당첨통계\n---\n";
    private static final String PURCHASE_INFO_MESSAGE = "%d개를 구매했습니다.";
    private static final String RATE_OF_RETURN_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public static void displayUserLottos(List<Lotto> userLottos) {
        String purchaseHeader = String.format(PURCHASE_INFO_MESSAGE, userLottos.size());
        System.out.println(purchaseHeader);
        userLottos.forEach(userLotto -> {
            System.out.println(userLotto.getDisplayFormat());
        });
    }

    public static void displayResult(Map<Rank, Integer> result, float rateOfReturn) {
        System.out.print(RESULT_HEADER + LottoUtil.convertResultToDisplayFormat(result));
        System.out.printf(RATE_OF_RETURN_MESSAGE, rateOfReturn);
    }
}
