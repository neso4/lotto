package lotto.util;

import java.util.List;

public class Validation {
    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final String NUMBER_FORMAT = " 입력값은 숫자여야 합니다.";
    private static final String NO_BLANK = " 입력값은 빈칸이 될 수 없습니다.";
    private static final String DIVISIBLE_PRICE = " 로또 구입 금액은 1000으로 나누어 떨어져야 합니다.";
    private static final String LOTTO_LENGTH = " 로또 번호는 6자리여야 합니다.";
    private static final String LOTTO_DUPLICATED = " 로또 번호는 중복될 수 없습니다";
    private static final String LOTTO_RANGE = " 로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    // 금액에 대한 validation
    public static void validatePrice(String input) {
        validateNumberFormat(input);
        validateEmptySpace(input);
        int inputNum = Integer.parseInt(input);
        validateDivisiblePrice(inputNum);
    }

    private static void validateNumberFormat(String input) {
        if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NUMBER_FORMAT);
        }
    }

    private static void validateEmptySpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NO_BLANK);
        }
    }

    private static void validateDivisiblePrice(int input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DIVISIBLE_PRICE);
        }
    }


    // 당첨 로또(입력)에 대한 validation
    public static void validateLottoNumbers(List<Integer> numbers) {
        validateNumberLength(numbers);
        validateNumberDuplicated(numbers);
        validateNumberRange(numbers);
    }

    private static void validateNumberLength(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE + LOTTO_LENGTH);
        }
    }

    private static void validateNumberDuplicated(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + LOTTO_DUPLICATED);
        }
    }

    private static void validateNumberRange(List<Integer> numbers) {
        if (isInvalid(numbers)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + LOTTO_RANGE);
        }
    }

    private static boolean isInvalid(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < 1 || number > 45);
    }


    public static void validateBonusNumber(String bonusNumberInput, List<Integer> winningNumbers) {
        int bonusNumber = validateBonusNumberFormat(bonusNumberInput);
        validateBonusNumberRange(bonusNumber);
        validateNumberNotDuplicated(bonusNumber, winningNumbers);
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ERROR_MESSAGE + LOTTO_RANGE);
        }
    }

    private static void validateNumberNotDuplicated(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + LOTTO_DUPLICATED);
        }
    }

    private static int validateBonusNumberFormat(String bonusNumberInput) {
        String numberInput = bonusNumberInput.trim();
        if (!numberInput.matches("[0-9]+")) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NUMBER_FORMAT);
        }
        return Integer.parseInt(numberInput);
    }
}