package lotto.validators;

import static lotto.constant.ErrorMessage.INPUT_WINNING_NUMBER_COUNT_IS_INCORRECT;
import static lotto.constant.ErrorMessage.INPUT_WINNING_NUMBER_DUPLICATED;
import static lotto.constant.ErrorMessage.INPUT_WINNING_NUMBER_VALUE_OUT_OF_RANGE;
import static lotto.constant.LottoInfo.LOTTO_NUMBER_COUNT;
import static lotto.constant.LottoInfo.MAX_LOTTO_NUMBER;
import static lotto.constant.LottoInfo.MIN_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumberValidator {
    public static void validWinningNumberCount(String[] winningNumbers) {
        if (!isCorrectWinningNumberCount(winningNumbers)) {
            throw new IllegalArgumentException(INPUT_WINNING_NUMBER_COUNT_IS_INCORRECT);
        }
    }

    public static void validWinningNumberValueInRange(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (!isWinningNumberValueInRange(winningNumber)) {
                throw new IllegalArgumentException(INPUT_WINNING_NUMBER_VALUE_OUT_OF_RANGE);
            }
        }
    }

    public static void validWinningNumberDuplicated(List<Integer> winningNumbers) {
        if (isContainsDuplicatedNumber(winningNumbers)) {
            throw new IllegalArgumentException(INPUT_WINNING_NUMBER_DUPLICATED);
        }
    }

    private static boolean isCorrectWinningNumberCount(String[] winningNumbers) {
        return winningNumbers.length == LOTTO_NUMBER_COUNT;
    }

    private static boolean isWinningNumberValueInRange(Integer winningNumber) {
        return winningNumber >= MIN_LOTTO_NUMBER && winningNumber <= MAX_LOTTO_NUMBER;
    }

    private static boolean isContainsDuplicatedNumber(List<Integer> winningNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(winningNumbers);
        return uniqueNumbers.size() != winningNumbers.size();
    }
}
