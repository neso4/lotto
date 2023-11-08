package lotto.model;

import java.util.List;

public class BonusNumber {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private static int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        BonusNumber.bonusNumber = bonusNumber;
    }

    public static int getBonusNumber() {
        return bonusNumber;
    }

    public static void validate(int bonusNumber) {
        if (!isBonusNumbersValid(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isBonusNumbersValid(int bonusNumber) {
        boolean duplicatesValid = isBonusNumberInWinningNumbers(bonusNumber);
        boolean boundsValid = checkInputBounds(bonusNumber);
        return duplicatesValid && boundsValid;
    }

    private static boolean isBonusNumberInWinningNumbers(int bonusNumber) {
        return !containsNumber(WinningNumbers.getWinningNumbers(), bonusNumber);
    }

    private static boolean containsNumber(List<Integer> numbers, int target) {
        for (int number : numbers) {
            if (number == target) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkInputBounds(int bonusNumber) {
        return LOTTO_NUMBER_MIN <= bonusNumber && bonusNumber <= LOTTO_NUMBER_MAX;
    }
}