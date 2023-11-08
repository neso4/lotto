package lotto;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplication(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되면 안됩니다.");
        }
    }

    public int getRank(List<Integer> winNums, int bonusNum) {
        long correctCount = numbers.stream()
                .filter(number -> winNums.contains(number))
                .count();

        int rank = calculateRank((int) correctCount, bonusNum);
        return rank;
    }

    private int calculateRank(int correctCount, int bonusNum) {
        if(correctCount == 6) {
            return 1;
        }

        if (correctCount == 5 && numbers.contains(bonusNum)) {
            return 2;
        }

        int rank = (correctCount >= 3) ? 8 - (int) correctCount : -1;
        return rank;
    }
}
