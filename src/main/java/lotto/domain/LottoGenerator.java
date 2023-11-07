package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LottoGenerator {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;

    public List<Lotto> generateLottoBundle(int price) {
        Lotto[] lottoBundle = new Lotto[price / 1000];
        for (int i = 0; i < price / 1000; i++) {
            lottoBundle[i] = generate();
        }
        return Arrays.asList(lottoBundle);
    }

    private Lotto generate() {
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, LOTTO_SIZE);
        lotto.sort(Comparator.naturalOrder());
        return new Lotto(lotto);
    }
}
