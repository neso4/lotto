package lotto.domain;

import static lotto.constants.Constants.*;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    public int getDuplicatedNumberCount(Lotto lotto) {
        long count = numbers.stream()
                .filter(l -> lotto.getNumbers().stream()
                        .anyMatch(Predicate.isEqual(l))).count();

        return (int) count;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumbersRange(numbers);
        validateNumbersForDuplicates(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateSingleNumberRange(number);
        }
    }

    protected void validateSingleNumberRange(Integer number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45사이의 숫자여야 합니다.");
        }
    }

    private void validateNumbersForDuplicates(List<Integer> numbers) {
        HashSet<Integer> distinctNumbers = new HashSet<>(numbers);
        if (numbers.size() != distinctNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}