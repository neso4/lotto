package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Generator {

    public List<Integer> lottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
