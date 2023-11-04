package lotto.view;

import java.util.List;
import lotto.model.Lotto;

public class LottoOutputView {

    public static final String PLEASE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String NUMBER_OF_PURCHASES = "\n%d개를 구매했습니다.";

    public static void printPurchaseAmount() {
        System.out.println(PLEASE_PURCHASE_AMOUNT);
    }

    /**
     * 구매한 로또를 출력한다.
     *
     * @param lottos 로또(들)을 받는다.
     */
    public static void printPurchaseLotto(List<Lotto> lottos) {
        String makeMessage = String.format(NUMBER_OF_PURCHASES, lottos.size());
        System.out.println(makeMessage);

        for (Lotto lotto :
                lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
    
}
