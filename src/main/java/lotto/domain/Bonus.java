package lotto.domain;

import static lotto.constants.ErrorMessage.DUPLICATE_BONUS;
import static lotto.constants.ErrorMessage.NUMBER_OUT_OF_RANGE;

import java.util.List;

public class Bonus {
    private static final int START_LOTTO_NUMBER = 1;
    private static final int END_LOTTO_NUMBER = 45;

    private final int number;

    public Bonus(int number, Lotto winningLotto) {
        validateNumberInRange(number);
        validateNotInWinningNumbers(number, winningLotto);

        this.number = number;
    }

    public static Bonus createWithValidate(int number, Lotto winningLotto) {
        return new Bonus(number, winningLotto);
    }

    private void validateNumberInRange(int number) {
        if (number < START_LOTTO_NUMBER || number > END_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format(NUMBER_OUT_OF_RANGE.getMessage(), START_LOTTO_NUMBER, END_LOTTO_NUMBER));
        }
    }

    private void validateNotInWinningNumbers(int number, Lotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();

        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }
}
