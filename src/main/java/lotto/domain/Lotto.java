package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public int calcMatchNumber(Lotto lottoToCompare) {
        Set<Integer> winningNumbers = new HashSet<>(lottoToCompare.getNumbers());
        int matchCount = 0;

        for (int number : numbers) {
            if (winningNumbers.contains(number)) {
                matchCount += 1;
            }
        }

        return matchCount;
    }

    public boolean isNumberInLotto(int numberToCompare) {
        for (int number : numbers) {
            if (numberToCompare == number) {
                return true;
            }
        }

        return false;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
