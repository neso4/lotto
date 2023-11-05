package lotto.model;

import java.util.List;

public class Lotto {
    // 필드 추가 불가능
    // numbers의 접근제어자인 private 변경 불가능
    // 제공된 Lotto 클래스를 활용해 구현해야함
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        // validate(numbers);
        this.numbers = numbers;
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != Constants.LOTTO_NUMBER_COUNTS) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_INVALID_ERROR.getMessage());
        }
    }
}
