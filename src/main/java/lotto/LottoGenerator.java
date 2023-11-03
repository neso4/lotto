package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoGenerator {

    public static final int LOTTO_START_INCLUSIVE = 1;
    public static final int LOTTO_END_INCLUSIVE = 45;
    public static final int LOTTO_STANDARD_COUNT = 6;

    public Lotto lottoGenerate() {
        return new Lotto(randomNumbersGenerate());
    }

    private List<Integer> randomNumbersGenerate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LOTTO_START_INCLUSIVE,
                LOTTO_END_INCLUSIVE, LOTTO_STANDARD_COUNT);
        return numbers;
    }
}
