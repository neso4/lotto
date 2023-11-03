package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.exception.LottoNumberException;
import lotto.vo.Number;

import java.util.*;

public class LottoAutoGenerator implements LottoGenerator {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    @Override
    public Lotto generate() {
        return null;
    }

    @Override
    public Lottos generate(int count) throws LottoNumberException {
        Set<Lotto> uniqueLottos = new HashSet<>();

        while (uniqueLottos.size() < count) {
            List<Integer> randomNumbers = generateRandomNumbers();
            List<Number> numbers = generateCreateNumbers(randomNumbers);
            Lotto lotto = generateLotto(numbers);
            uniqueLottos.add(lotto);
        }

        return new Lottos(new ArrayList<>(uniqueLottos)); // 명시적 형변환
    }

    private static List<Integer> generateRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_SIZE);
    }

    private static List<Number> generateCreateNumbers(List<Integer> randomNumbers) throws LottoNumberException {
        return randomNumbers.stream()
                .map(Number::new)
                .toList();
    }

    private static Lotto generateLotto(List<Number> randomNumbers) {
        return new Lotto(randomNumbers);
    }
}
