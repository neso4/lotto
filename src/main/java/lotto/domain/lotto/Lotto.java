package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateNumberRange(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 1부터 45 사이여야합니다.");
        }

    }
    private boolean isOutOfRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

    private void validateDuplication(List<Integer> numbers) {
        if (hasDistinctNumberIn(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 중복되었습니다.");
        }
    }

    private boolean hasDistinctNumberIn(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .toList()
                .size()
                != numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean hasSameNumber(BonusNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(bonusNumber::hasSameNumber);
    }
}
