package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final String ERROR_NUMBER_RANGE =
            String.format("%d부터 %d까지의 숫자만 입력 가능합니다.", MIN, MAX);

    private static final String ERROR_NUMBER_COUNT = "6개의 숫자를 입력해 주세요.";
    private static final String ERROR_NUMBER_DUPLICATE = "중복되지 않는 숫자를 입력해 주세요.";


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_NUMBER_COUNT);
        } else if (!isWithinRangeNum(numbers)) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        } else if (!isNotDuplicateNum(numbers)) {
            throw new IllegalArgumentException(ERROR_NUMBER_DUPLICATE);
        }
    }

    private boolean isNotDuplicateNum(List<Integer> numbers) {
        return numbers.stream().distinct().count() == numbers.size();
    }

    private boolean isWithinRangeNum(List<Integer> numbers) {
        return numbers.stream()
                .allMatch(number -> number >= MIN && number <= MAX);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
