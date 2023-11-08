package lotto.domain;

import java.util.List;
import lotto.validation.Validation;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        Validation.validateListSize(numbers);
        Validation.validateDuplicateNumber(numbers);
        Validation.validateListOutOfRangeNumber(numbers);
    }
}
