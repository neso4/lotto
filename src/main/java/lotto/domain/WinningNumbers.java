package lotto.domain;

import java.util.HashSet;
import java.util.List;
import lotto.exception.LottoExceptionMessage;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    private WinningNumbers(List<Integer> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    public static WinningNumbers from(List<Integer> winningNumbers) {
        try {
            return new WinningNumbers(winningNumbers);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void validate(List<Integer> winningNumbers) {
        isSixUniqueNumbers(winningNumbers);
        isBetweenStartAndEndInclusive(winningNumbers);
    }

    private void isSixUniqueNumbers(List<Integer> winningNumbers) {
        HashSet<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != 6) {
            System.out.println(LottoExceptionMessage.WINNING_NUMBERS_MUST_BE_SIX_DIGITS.getMessage());

            throw new IllegalArgumentException(LottoExceptionMessage.WINNING_NUMBERS_MUST_BE_SIX_DIGITS.getMessage());
        }
    }

    private void isBetweenStartAndEndInclusive(List<Integer> winningNumbers) {
        if (winningNumbers.stream().anyMatch(number -> number < 1 || number > 45)) {
            System.out.println(LottoExceptionMessage.WINNING_NUMBERS_MUST_BETWEEN_START_AND_END_INCLUSIVE.getMessage());
            throw new IllegalArgumentException(
                    LottoExceptionMessage.WINNING_NUMBERS_MUST_BETWEEN_START_AND_END_INCLUSIVE.getMessage());
        }
    }
}
