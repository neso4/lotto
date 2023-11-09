package lotto.domain;

import java.util.List;
import java.util.Objects;
import lotto.utils.ValidationUtil;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int calcCorrectNumbers(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validate(List<Integer> numbers) {
        ValidationUtil.validateHasSixNumbers(numbers);
        ValidationUtil.validateNoDuplicatedNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }


    public boolean containsBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
