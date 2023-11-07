package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    public static final int MAX_NUMBERS = 6;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Lotto() {
        this.numbers = new ArrayList<>();
    }

    private void isInRange(List<Integer> numbers) {
        for (int num : numbers) {
            if (num < MIN_VALUE || num > MAX_VALUE) {
                throw new IllegalArgumentException("로또 번호는 1에서 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void checkLength(List<Integer> numbers) {
        if (numbers.size() != MAX_NUMBERS) {
            throw new IllegalArgumentException("로또 번호는 6개 입력해야 합니다.");
        }
    }

    protected void validate(List<Integer> numbers) {
        checkLength(numbers);
        isInRange(numbers);
        isUnique(numbers);
    }

    private void isUnique(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("중복된 로또 번호가 있습니다.");
        }
    }

    protected void isUnique(List<Integer> numbers, int bonusNumber) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호가 중복됩니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
