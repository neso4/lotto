package lotto.domain;

import lotto.utils.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.utils.ErrorMessage.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_INPUT_SIZE.getExceptionMessage());
        }

        for (Integer number : numbers) {
            if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
                throw new IllegalArgumentException(INVALID_INPUT_NUMBER.getExceptionMessage());
            }
        }

        Set<Integer> duplicateNumber = new HashSet<>(numbers);
        if (duplicateNumber.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_INPUT_NUMBER.getExceptionMessage());
        }

        System.out.println(numbers);
    }
}
