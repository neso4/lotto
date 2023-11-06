package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개 숫가로 구성되어 있어야 합니다.");
        }
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재해서는 안 됩니다.");
        }


    }

    // TODO: 추가 기능 구현
    public List<Integer> getNumbers() {
        return numbers;
    }
}
