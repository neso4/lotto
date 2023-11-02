package lotto.validation;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Validation {
    public void validateUnit(int payment) {
        if(payment % 1000 != 0) {
            throw new IllegalArgumentException("입력값이 1,000원 단위가 아닙니다.");
        }
    }

    public void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("입력된 숫자가 6개가 아닙니다.");
        }
    }

    public void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if(number < 1 || 45 < number) {
                throw new IllegalArgumentException("숫자 범위는 1 이상 45 이하여야 합니다.");
            }
        }
    }

    public void validateDuplication(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if(numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }
}