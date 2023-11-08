package lotto.domain;

import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public void sort() {
        this.numbers.sort(Comparator.naturalOrder());
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size()!=6) {
            throw new IllegalArgumentException();
        }
    }
}
