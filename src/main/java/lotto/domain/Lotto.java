package lotto.domain;

import static lotto.domain.Lotto.LottoErrorMessage.COUNT_NOT_SIX;
import static lotto.domain.Lotto.LottoErrorMessage.HAS_DUPLICATE_NUBMER;
import static lotto.domain.Lotto.LottoErrorMessage.NOT_IN_RANGE;

import java.util.List;

public class Lotto {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    private static final int SIX = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIX) {
            throw new IllegalArgumentException(COUNT_NOT_SIX.getErrorMessage());
        }
        if (hasDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(HAS_DUPLICATE_NUBMER.getErrorMessage());
        }
        if (hasNumberNotInRange(numbers)) {
            throw new IllegalArgumentException(NOT_IN_RANGE.getErrorMessage());
        }
    }

    private boolean hasNumberNotInRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < MIN_RANGE || number > MAX_RANGE);
    }

    private boolean hasDuplicateNumber(List<Integer> numbers) {
        List<Integer> distinctedNumbers = numbers.stream()
                .distinct()
                .toList();

        return !distinctedNumbers.equals(numbers);
    }

    public boolean isContain(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    enum LottoErrorMessage {
        COUNT_NOT_SIX("[ERROR] 6개의 숫자로 이루어져야 합니다."),
        HAS_DUPLICATE_NUBMER("[ERROR] 중복된 숫자는 존재할 수 없습니다."),
        NOT_IN_RANGE("[ERROR] 모든 숫자는 1부터 45까지의 값만 가능합니다.");

        private final String errorMessage;

        LottoErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
