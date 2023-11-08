package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.domain.lotto.LottoCondition.*;

public class RandomNumberGenerator implements NumberGenerator {

    private RandomNumberGenerator() {
    }

    private static class NumberGeneratorHolder {
        private static RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    }

    public static RandomNumberGenerator getInstance() {
        return NumberGeneratorHolder.randomNumberGenerator;
    }

    @Override
    public List<Integer> generateNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER.getValue(), MAX_NUMBER.getValue(), COUNT.getValue());
    }
}
