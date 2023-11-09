package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.controller.NumberPickingStrategy;

public class NumberPickingStrategyImpl implements NumberPickingStrategy {
    @Override
    public List<Integer> pickLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
