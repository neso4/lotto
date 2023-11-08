package lotto.domain.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int checkWinningNumber(Lotto winningNumbers) {
        int matchingCount = 0;
        for (int number:this.numbers) {
            for (int winningNumber: winningNumbers.numbers) {
                if (number == winningNumber) {
                    matchingCount++;
                }
            }
        }
        return matchingCount;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
