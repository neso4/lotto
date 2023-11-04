package lotto.service;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static lotto.model.Lotto.LOTTO_PRICE;
import static lotto.model.Lotto.MAXIMUM_NUMBER;
import static lotto.model.Lotto.MINIMUM_NUMBER;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.LottoPriceUnitException;
import lotto.model.Lotto;
import lotto.model.Money;

public class LottoService {

    public List<Lotto> buyLottos(Money money) {
        validateUnit(money);
        int count = money.getMoney() / LOTTO_PRICE;

        return issuanceLotto(count);
    }

    /**
     * 로또를 발행한다.
     *
     * @param count 발행할 로또 수
     * @return 발행한 로또(들)을 반환
     */
    private List<Lotto> issuanceLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) { // 갯수만큼 로또를 발행한다.
            lottos.add(makeLotto());
        }

        return lottos;
    }

    /**
     * 로또를 생성한다.
     *
     * @return Lotto
     */
    private Lotto makeLotto() {
        List<Integer> numbers = pickUniqueNumbersInRange(MINIMUM_NUMBER, MAXIMUM_NUMBER, 6);
        Lotto lotto = new Lotto(numbers);

        return lotto;
    }

    /**
     * 로또 구입 금액이 1,000 단위가 맞는지 확인한다.
     */
    public void validateUnit(Money money) {
        int remain = money.getMoney() % LOTTO_PRICE;
        if (remain != 0) {
            throw new LottoPriceUnitException();
        }
    }
}
