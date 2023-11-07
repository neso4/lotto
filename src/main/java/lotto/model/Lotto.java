package lotto.model;

import lotto.view.ExceptionMessage;

import java.util.*;

public class Lotto {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSixDigits(numbers);
        validateDuplicatedNumber(numbers);
        validateRange(numbers);
        List<Integer> tempNumbers = new ArrayList<>(numbers);
        Collections.sort(tempNumbers);
        this.numbers = tempNumbers;
    }

    private void validateSixDigits(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

}
