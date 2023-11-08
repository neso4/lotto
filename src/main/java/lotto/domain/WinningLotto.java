package lotto.domain;

import java.util.List;
import java.util.Objects;

public class WinningLotto {

    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningLotto that = (WinningLotto) o;
        return bonusNumber == that.bonusNumber && Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, bonusNumber);
    }
}
