package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.Bonus;
import lotto.model.Lotto;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;


    public List<Lotto> publishLottos(int price) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = price/LOTTO_PRICE;
        while (lottoCount-- > 0) {
            List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(
                    LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBER_COUNT));
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return lottos;
    }

    public int getMatchCount(Lotto lotto, Lotto winningNumbers) {
        int matchCount = 0;
        for (Integer lottoNumber : lotto.getNumbers()) {
            if (winningNumbers.getNumbers().contains(lottoNumber)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean getIsMatchBonus(Lotto lotto, Bonus bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber.getNumber())) {
            return true;
        }
        return false;
    }
}
