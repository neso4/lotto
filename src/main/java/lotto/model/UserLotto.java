package lotto.model;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.LottoException;

public class UserLotto {

    private int lottoCount;
    private List<Lotto> lottoBundle;

    public UserLotto(int money) {
        moneyToCount(money);
        publishLottoBundle();
    }

    private void moneyToCount(int money) {
        validateNegativeMoney(money);
        validateMoneyUnit(money);
        this.lottoCount = money / 1000;
    }

    private void publishLottoBundle() {
        lottoBundle = new ArrayList<>();
        Lotto newLotto;

        for (int i = 0; i < lottoCount; i++) {
            newLotto = publishLotto();
            lottoBundle.add(newLotto);
        }
    }

    private Lotto publishLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    private void validateNegativeMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(LottoException.NOT_NEGATIVE_NUM.getExceptionMessage());
        }
    }

    private void validateMoneyUnit(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(LottoException.INVALID_MONEY_UNIT.getExceptionMessage());
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle;
    }
}
