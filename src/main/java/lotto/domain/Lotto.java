package lotto.domain;

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
        if (hasDuplicates(numbers)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasDuplicates(List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }
}
