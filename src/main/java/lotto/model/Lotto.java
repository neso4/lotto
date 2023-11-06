package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public Boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public Integer compare(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return "[" + numbers.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + "]";
    }
}
