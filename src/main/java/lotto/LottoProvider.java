package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoProvider {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_NUMBER_CNT = 6;

    public List<Lotto> buyLottos(String purchaseAmount) {
        int purchasableCount = getPurchasableCount(purchaseAmount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i=0; i<purchasableCount; i++) {
            List<Integer> lottoNumbers = generateLottoNumbers();
            Lotto lotto = new Lotto(lottoNumbers);
            lottos.add(lotto);
        }

        return lottos;
    }

    private List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_START_NUMBER, LOTTO_END_NUMBER, LOTTO_NUMBER_CNT);
    }

    private int getPurchasableCount(String input) {
        // 숫자가 아닌 경우 예외 발생
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }

        int purchaseAmount = Integer.parseInt(input);
        if (purchaseAmount%LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
        return purchaseAmount / LOTTO_PRICE;
    }
}
