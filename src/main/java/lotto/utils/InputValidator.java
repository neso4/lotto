package lotto.utils;

import lotto.enums.ErrorMessage;

public class InputValidator {
    private static final int LESS_AMOUNT = 1000;
    private static final int JACKPOT_MINIMUM_NUMBER = 1;
    private static final int JACKPOT_MAXIMUM_NUMBER = 45;
    private static final int JACKPOT_MAXIMUM_SIZE = 6;
    private static final int BONUS_MAXIMUM_SIZE = 1;

    public static void checkLottoPayAmountInput(String lottoPayAmount) {
        checkNumeric(lottoPayAmount);
        checkLessAmount(lottoPayAmount);
        checkLessAmountUnit(lottoPayAmount);
    }

    private static void checkLessAmountUnit(String lottoPayAmount) {
        if (Integer.parseInt(lottoPayAmount) % LESS_AMOUNT != 0) {
            throw new IllegalArgumentException(ErrorMessage.LESS_AMOUNT_UNIT_ERROR.getErrorMessage());
        }
    }

    private static void checkLessAmount(String lottoPayAmount) {
        if (Integer.parseInt(lottoPayAmount) < LESS_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.LESS_AMOUNT_ERROR.getErrorMessage());
        }
    }

    private static void checkNumeric(String lottoPayAmount) {
        for (int i = 0; i < lottoPayAmount.length(); i++) {
            if (!Character.isDigit(lottoPayAmount.charAt(i))) {
                throw new IllegalArgumentException(ErrorMessage.NUMERIC_ERROR.getErrorMessage());
            }
        }
    }

    public static void checkJackpotNumberInput(String jackpotNumberInput) {
        checkHasComma(jackpotNumberInput);
        checkContainSpace(jackpotNumberInput);
        checkJackpotNumberNumeric(jackpotNumberInput);
        checkJackpotNumberSize(jackpotNumberInput);
        checkEndsWithComma(jackpotNumberInput);
        checkNumberOfRange(jackpotNumberInput);
        // TODO : 중복된 숫자를 입력했을 경우

    }

    private static void checkNumberOfRange(String jackpotNumberInput) {
        String replaceNumberInput = jackpotNumberInput.replace(",", "");
        for (int i = 0; i < replaceNumberInput.length(); i++) {
            int number = Integer.parseInt(String.valueOf(replaceNumberInput.charAt(i)));
            if (number < JACKPOT_MINIMUM_NUMBER || JACKPOT_MAXIMUM_NUMBER < number) {
                throw new IllegalArgumentException(ErrorMessage.JACKPOT_NUMBER_OUT_OF_RANGE_ERROR.getErrorMessage());
            }
        }
    }

    private static void checkEndsWithComma(String jackpotNumberInput) {
        if (jackpotNumberInput.charAt(jackpotNumberInput.length() - 1) == ',') {
            throw new IllegalArgumentException(ErrorMessage.END_WITH_COMMA_ERROR.getErrorMessage());
        }
    }

    private static void checkJackpotNumberSize(String jackpotNumberInput) {
        String[] jackpotNumbers = jackpotNumberInput.split(",");
        if (jackpotNumbers.length != JACKPOT_MAXIMUM_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.JACKPOT_NUMBER_SIZE_ERROR.getErrorMessage());
        }
    }

    private static void checkJackpotNumberNumeric(String jackpotNumberInput) {
        String replaceNumberInput = jackpotNumberInput.replace(",", "");
        checkNumeric(replaceNumberInput);
    }

    private static void checkContainSpace(String jackpotNumberInput) {
        if (jackpotNumberInput.contains(" ")) {
            throw new IllegalArgumentException(ErrorMessage.HAS_SPACE_ERROR.getErrorMessage());
        }
    }

    private static void checkHasComma(String jackpotNumberInput) {
        if (!jackpotNumberInput.contains(",")) {
            throw new IllegalArgumentException(ErrorMessage.HAS_NOT_COMMA_ERROR.getErrorMessage());
        }
    }

    public static void checkBonusNumberInput(String bonusNumberInput) {
        checkBonusNumberSize(bonusNumberInput);
        // TODO : 중복된 숫자를 입력했을 경우
    }

    private static void checkBonusNumberSize(String bonusNumberInput) {
        if (bonusNumberInput.length() == 0) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_SIZE_ERROR.getErrorMessage());
        }
        String[] bonusNumbers = bonusNumberInput.split(",");
        if (bonusNumbers.length != BONUS_MAXIMUM_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_SIZE_ERROR.getErrorMessage());
        }
    }
}
