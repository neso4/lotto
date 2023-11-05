package lotto.model;

import java.util.List;

public class Lotto {
    private final static int LOTTO_NUMBER_COUNT = 6;
    private static final String LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 로또 숫자 6개를 입력해주세요.";
    private static final String NON_DUPLICATE_NUMBER_ERROR_MESSAGE = "[ERROR] 중복되지 않은 숫자를 입력해주세요.";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    private void validate(List<Integer> numbers) {
        checkNumbersSize(numbers);
        checkForDuplicates(numbers);
    }

    private void checkForDuplicates(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(NON_DUPLICATE_NUMBER_ERROR_MESSAGE);
        }
    }

    private void checkNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
