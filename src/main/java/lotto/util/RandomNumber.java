package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumber {
    private RandomNumber() {
    }

    public static List<Integer> generateLotteryNumber() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
