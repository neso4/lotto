package view;

import java.util.List;
import lotto.Lotto;

public class OutputView {

    private OutputView() {}

    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
    }
}
