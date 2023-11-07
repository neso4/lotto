package lotto.model;

import static lotto.model.SystemConstant.MAX_LOTTO_NUMBER;
import static lotto.model.SystemConstant.MIN_LOTTO_NUMBER;
import static lotto.view.ErrorMessage.DUPLICATED_NUMBER;
import static lotto.view.ErrorMessage.OUT_OF_RANGE_LOTTO_NUMBERS;

import java.util.Set;

public class Bonus {
    private int number;

    public Bonus(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        validateRangeOfNumbers(number);
    }

    private void validateRangeOfNumbers(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_NUMBERS.getMessage());
        }
    }

    public int getNumber() {
        return this.number;
    }

    public void validateWinningNumbers(Set<Integer> winningNumbers) {
        if (winningNumbers.contains(this.number)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }
}
