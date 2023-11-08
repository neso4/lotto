package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }
    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException();
        }
    }
    private void validateNumbersInRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException();
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public LottoReward getRewardWithMatchedNumbers(WinningLotto winningLotto) {
        int matchedCount = countMatchedNumbers(winningLotto.getLotto());
        boolean bonusNumberMatched = isBonusNumberMatched(winningLotto.getBonusNumber());
        return LottoReward.getReward(matchedCount, bonusNumberMatched);
    }

    private int countMatchedNumbers(Lotto winningNumbers) {
        List<Integer> winningNumberList = winningNumbers.getNumbers();
        int count = 0;
        for (Integer number : winningNumberList) {
            if (this.numbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    private boolean isBonusNumberMatched(Integer bonusNumber) {
        return numbers.contains(bonusNumber);
    }

}
