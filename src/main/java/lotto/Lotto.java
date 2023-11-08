package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int START = 1;
    private static final int END = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        duplicate(numbers);
        rangeCheck(numbers);

        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6자리여야 합니다.");
        }
    }

    private void duplicate(List<Integer> numbers) {
        Set<Integer> num = new HashSet<>(numbers);
        if (num.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복이 없어야 합니다.");
        }
    }

    private void rangeCheck(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < START || number > END) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45내의 정수입니다.");
            }
        }
    }

    public List<Integer> getLottoNumbers() {
        return numbers;
    }

    public int countSameNumber(Lotto winner) {
        int sameCount = 0;

        for (int number : numbers) {
            if (winner.getLottoNumbers().contains(number)) {
                sameCount += 1;
            }
        }

        return sameCount;
    }

    public boolean containsBonusNumber(int bonusNumber) {
        for (int number : numbers) {
            if (number == bonusNumber) {
                return true;
            }
        }

        return false;
    }
}
