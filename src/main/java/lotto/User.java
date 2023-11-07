package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int LOTTO_PRICE = Price.LOTTO.getLottoPrice();
    private int haveSeveralLotto = 0;

    public int inputAmount(int allLottoPay) {
        if (allLottoPay % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 구입 금액이 맞지 않습니다.");
        }

        haveSeveralLotto = allLottoPay / LOTTO_PRICE;
        return haveSeveralLotto;
    }

    public List<Lotto> saveLottos() {
        List<Lotto> lottos = new ArrayList<>();

        for(int i=0; i<haveSeveralLotto; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

}