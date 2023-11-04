package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.Lotto;

public final class LottoGenerator {
    private final static int SIZE_LOTTO_NUMBER = 6;
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private LottoGenerator() {

    }

    public static Lotto autoGenerate() {
        List<Integer> randomNumbers = generateRandomNumbers();
        return new Lotto(randomNumbers);
    }

    private static List<Integer> generateRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, SIZE_LOTTO_NUMBER);
    }

}
