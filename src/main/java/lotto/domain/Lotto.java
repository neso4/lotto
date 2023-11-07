package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.utils.Validate;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        Validate.hasDuplicateNumbers(numbers);
    }

    // TODO: 추가 기능 구현

    public static Lotto generateLottery() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
