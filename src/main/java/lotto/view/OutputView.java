package lotto.view;

import java.util.List;
import lotto.model.Referee;
import lotto.model.Lotto;

public class OutputView {
    public void printCreateLottoNumbers(final List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.size());
        lottos.forEach(lotto -> System.out.println(lotto.toString()));
    }

    public void printResult(final Referee referee, final double rateOfReturn) {
        System.out.println(referee);
        System.out.printf("총 수익률은 %.1f%%입니다.", rateOfReturn);
    }
}
