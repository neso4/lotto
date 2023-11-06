package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    public List<Integer> issueSixNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

}
