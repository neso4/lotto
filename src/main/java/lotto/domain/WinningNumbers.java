package lotto.domain;

import java.util.List;

import static lotto.policy.LottoNumberGeneratePolicy.MAX_NUM;
import static lotto.policy.LottoNumberGeneratePolicy.MIN_NUM;

public class WinningNumbers {

    public static final int WINNING_NUM_SIZE = 6;

    private final List<Integer> numbers;

    public static WinningNumbers of(List<Integer> numbers) {
        return new WinningNumbers(numbers);
    }

    private WinningNumbers(List<Integer> numbers) {
        validateLength(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    public boolean hasDuplicate(int number) {
        return this.numbers.contains(number);
    }

    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUM_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRange(List<Integer> numbers) {
        numbers.stream().filter(number -> number < MIN_NUM || number > MAX_NUM)
                .findAny()
                .ifPresent(number -> {
                    throw new IllegalArgumentException();
                });
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (hasDuplicatedNumber(numbers)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasDuplicatedNumber(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count() != WINNING_NUM_SIZE;
    }

}
