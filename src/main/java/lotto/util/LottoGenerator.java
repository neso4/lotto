package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoGenerator {

    public List<Integer> generateRandomNumList() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
