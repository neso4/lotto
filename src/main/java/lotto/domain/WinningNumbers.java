package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.utils.Constants;
import lotto.utils.ErrorMessage;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(List<Integer> winningNumbersIntegerList) {
        validateWinningNumbers(winningNumbersIntegerList);
        this.winningNumbers = winningNumbersIntegerList;
    }

    public void validateWinningNumbers(List<Integer> winningNumbersIntegerList) {
        validateWinningNumbersRange(winningNumbersIntegerList);
        validateWinningNumberDuplication(winningNumbersIntegerList);
        validateWinningNumbersCount(winningNumbersIntegerList);
    }

    private void validateWinningNumbersRange(List<Integer> winningNumbers) {
        for (int number : winningNumbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(int number) {
        if (number < Constants.LOTTO_MIN_NUMBER || number > Constants.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBER_RANGE_INVALID_ERROR.getMessage());
        }
    }


    private void validateWinningNumberDuplication(List<Integer> winningNumbersIntegerList) {
        Set<Integer> numberSet = new HashSet<>();
        for (int number : winningNumbersIntegerList) {
            validateDuplication(numberSet, number);
        }
    }

    private void validateDuplication(Set<Integer> numberSet, int number) {
        if (!numberSet.add(number)) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBER_DUPLICATION_ERROR.getMessage());
        }
    }

    public boolean contains(int number) {
        return winningNumbers.contains(number);
    }

    private void validateWinningNumbersCount(List<Integer> winningNumbersIntegerList) {
        if (winningNumbersIntegerList.size() != Constants.LOTTO_NUMBER_COUNTS) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBER_COUNT_INVALID_ERROR.getMessage());
        }
    }
}
