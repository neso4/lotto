package lotto.Model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers +"";
    }
    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호를 6개 입력해 주세요.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> nonDuplicate = new HashSet<>(numbers);

        if (nonDuplicate.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될수 없습니다.");
        }
    }
}
