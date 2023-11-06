package lotto.domain;

import lotto.utils.InputException;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        InputException.checkSize(numbers);
        InputException.isDuplication(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
