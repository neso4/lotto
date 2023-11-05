package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constant.LottoNumber;

public class LottoMachine {
    public static List<Lotto> issueLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                    LottoNumber.MINIMUM.getValue(), LottoNumber.MAXIMUM.getValue(), LottoNumber.SIZE.getValue());
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers);
            lottos.add(new Lotto(sortedNumbers));
        }
        return lottos;
    }
}
