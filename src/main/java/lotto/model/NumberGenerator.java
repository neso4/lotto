package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class NumberGenerator {

    public static List<Integer> getLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
