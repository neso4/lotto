package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMachine {
    public Lotto excute() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
