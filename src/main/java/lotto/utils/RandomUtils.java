package lotto.utils;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomUtils {
    public static List<Integer> makeRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
