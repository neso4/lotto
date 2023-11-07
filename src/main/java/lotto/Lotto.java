package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("6자리가 아닙니다.");
        }
    }

    public boolean isNumberInLotto(int Number) {
        return numbers.contains(Number);
    }
    public int countMatchingNumbers(Lotto otherLotto) {
        return (int) this.numbers.stream()
                .filter(otherLotto.numbers::contains)
                .count();
    }
}
