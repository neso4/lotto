package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    private static final int MIN = 1;
    private static final int MAX = 45;

    public Lotto(List<Integer> numbers) {
        validate(numbers); //money는 input일때 단 한번만 객체르 생성하지만 lotto는 입력일때, 랜덤생성될때 여러 방면에서 객체가 생성되므로 생성자에서 유효검사를 하는게 좋다
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        } else if (!isWithinRangeNum(numbers, MIN, MAX)) {
            throw new IllegalArgumentException();
        } else if (!isNotDuplicateNum(numbers)) {
            throw new IllegalArgumentException();
        } else if (numbers.contains("")) {
            throw new IllegalArgumentException();
        }


    }

    private boolean isNotDuplicateNum(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> Collections.frequency(numbers, number) == 1);
    }

    private boolean isWithinRangeNum(List<Integer> numbers, int min, int max) {
        return numbers.stream()
                .allMatch(number -> number >= min && number <= max);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    // TODO: 추가 기능 구현
}
