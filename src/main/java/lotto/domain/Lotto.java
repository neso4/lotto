package lotto.domain;

import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int ALLOWED_NUMBER_SIZE = 6;
    private static final int START_NUMBER_IN_RANGE = 1;
    private static final int END_NUMBER_IN_RANGE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (!isAllowedNumberSize(numbers) || !isUniqueNumbers(numbers) || !isNumbersInRange(numbers)) {
            throw new IllegalArgumentException("유효하지 않은 로또 번호입니다.");
        }
    }

    private boolean isAllowedNumberSize(List<Integer> numbers) {
        return numbers.size() == ALLOWED_NUMBER_SIZE;
    }

    private boolean isUniqueNumbers(List<Integer> numbers) {
        return Set.copyOf(numbers)
                .size() == ALLOWED_NUMBER_SIZE;
    }

    private boolean isNumbersInRange(List<Integer> numbers) {
        return numbers.stream()
                .noneMatch(number -> number < START_NUMBER_IN_RANGE || number > END_NUMBER_IN_RANGE);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countEqualNumbers(List<Integer> comparedNumbers) {
        return (int) numbers.stream()
                .filter(comparedNumbers::contains)
                .count();
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }
}
