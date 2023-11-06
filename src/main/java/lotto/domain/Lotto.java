package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public List<Integer> sortLottoNumbers() {
        return this.numbers.stream()
                .sorted()
                .toList();
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        HashSet<Integer> duplicationChecker = new HashSet<>(numbers);

        if (duplicationChecker.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }
}