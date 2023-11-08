package lotto.model.lottoResultChecker;


import java.util.List;

import static lotto.constant.ErrorMessages.*;
import static lotto.constant.NumberConstant.MAX_NUMBER_RANGE;
import static lotto.constant.NumberConstant.MIN_NUMBER_RANGE;

public class LottoBonus {
    private final int bonusNumber;

    public LottoBonus(String input, List<Integer> winningNumbers) {
        int number = parseBonusNumber(input);
        validateBonusNumber(number, winningNumbers);
        this.bonusNumber = number;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private int parseBonusNumber(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_NUMBERS_ALLOWED );
        }
    }

    private void validateBonusNumber(int number, List<Integer> winningNumbers) {
        if (number < MIN_NUMBER_RANGE || number > MAX_NUMBER_RANGE ) {
            throw new IllegalArgumentException(NUMBER_RANGE);
        }
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE );
        }
    }
}






