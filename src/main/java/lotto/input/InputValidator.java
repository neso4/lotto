package lotto.input;

import static lotto.error.message.InvalidInputErrorMessage.*;

import java.util.HashSet;
import java.util.List;
import lotto.error.exception.InvalidInputException;
import lotto.util.IntegerUtil;

public class InputValidator {
    public void validatePurchaseAmount(Integer purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new InvalidInputException(PURCHASE_AMOUNT_UNDER_THOUSAND.getMessage(), purchaseAmount);
        }
        if (purchaseAmount % 1000 != 0) {
            throw new InvalidInputException(PURCHASE_AMOUNT_NOT_MULTIPLE_OF_THOUSAND.getMessage(), purchaseAmount);
        }
    }

    public void validateWinningNumbers(List<Integer> winningNumbers) {
        checkWinningNumbersHasSix(winningNumbers);

        for (Integer winningNumber : winningNumbers) {
            if (!IntegerUtil.checkNumberInRange(winningNumber, 1, 45)) {
                throw new InvalidInputException(OUT_OF_RANGE.getMessage(), winningNumber);
            }
        }
        checkWinningNumberIsDuplicate(winningNumbers);
    }

    private void checkWinningNumbersHasSix(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new InvalidInputException(WINNING_NUMBERS_COUNT_NOT_SIX.getMessage(), winningNumbers);
        }
    }

    private void checkWinningNumberIsDuplicate(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != 6) {
            throw new InvalidInputException(WINNING_NUMBERS_DUPLICATE.getMessage(), winningNumbers.toString());
        }
    }

    public void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        if (!IntegerUtil.checkNumberInRange(bonusNumber, 1, 45)) {
            throw new InvalidInputException(OUT_OF_RANGE.getMessage(), bonusNumber);
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new InvalidInputException(BONUS_NUMBER_IN_WINNING_NUMBER.getMessage(), bonusNumber);
        }
    }
}
