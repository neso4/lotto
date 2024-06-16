package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) throws MyException {
        validate(numbers);
        checkRange(numbers);
        this.numbers = numbers;
    }

    // 6자리인지 검사
    private void validate(List<Integer> numbers) throws IllegalArgumentException {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 숫자는 6자리여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현

    // 범위 확인
    private void checkRange(List<Integer> numbers) throws MyException {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new MyException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }
}
