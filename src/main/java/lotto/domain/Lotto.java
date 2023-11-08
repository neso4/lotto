package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Set<Integer> validateNumbers = new HashSet<>(numbers);

        if (numbers.size() != validateNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 숫자가 중복되었습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
