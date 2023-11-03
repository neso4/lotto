package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.model.Rank;
import lotto.util.LottoUtil;

public class OutputView {
    private static final String RESULT_HEADER = "당첨통계\n---\n";

    public static void displayUserLottos(List<Lotto> userLottos) {
        String purchaseHeader = String.format("%d개를 구매했습니다.", userLottos.size());
        System.out.println(purchaseHeader);
        userLottos.forEach(userLotto -> {
            System.out.println(userLotto.getDisplayFormat());
        });
    }

    public static void displayResult(Map<Rank, Integer> result, float rateOfReturn) {
        System.out.print(RESULT_HEADER + LottoUtil.convertResultToDisplayFormat(result));
        System.out.printf("총 수익률은 %.1f%% 입니다.", rateOfReturn);
    }
}
