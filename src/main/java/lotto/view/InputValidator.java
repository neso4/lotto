package lotto.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputValidator {
    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "숫자 형식 입력이 아닙니다.";
    private static final String INVALID_EMPTY_INPUT_MESSAGE = "입력이 빈칸입니다.";
    private static final String INVALID_CONTAIN_BLANK_INPUT_MESSAGE = "입력에 공백이 포함될 수 없습니다.";
    private static final String INVALID_BUY_AMOUNT_MESSAGE = "구입 금액을 1,000원 단위로 입력해주세요.";
    private static final String INVALID_LOTTO_NUMBER_RANGE = "로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String INVALID_LOTTO_NUMBER_SIZE = "로또 번호는 개수는 6개여야 합니다.";
    private static final String INVALID_LOTTO_NUMBER_DUPLICATE = "로또 번호는 중복될 수 없습니다.";
    private static final String EMPTY = "";
    private static final char BLANK = ' ';
    private static final int THOUSAND = 1000;
    private static final int ZERO = 0;
    private static final String COMMA = ",";
    private static final int VALID_LOTTO_START_NUMBER = 1;
    private static final int VALID_LOTTO_END_NUMBER = 45;
    private static final int VALID_LOTTO_NUMBERS_SIZE = 6;

    public static int validateBuyAmount(String inputBuyAmount) {
        validateEmpty(inputBuyAmount);
        validateContainBlank(inputBuyAmount);
        int buyAmount = convertStringToInteger(inputBuyAmount);
        validateRestZero(buyAmount);
        return buyAmount;
    }

    public static List<Integer> validateWinningNumbers(String inputWinningNumbers) {
        validateEmpty(inputWinningNumbers);
        validateContainBlank(inputWinningNumbers);
        List<Integer> winningNumbers = splitWinningNumbersByComma(inputWinningNumbers);
        validateNumberSize(winningNumbers);
        validateNumberDuplicate(winningNumbers);
        return winningNumbers;
    }

    public static int validateBonusNumber(String inputBonusNumber) {
        int bonusNumber = convertStringToInteger(inputBonusNumber);
        return bonusNumber;
    }

    private static void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != VALID_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    private static void validateNumberDuplicate(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        int sizeOfNumbers = numbers.size();
        int sieOfDistinctNumbers = distinctNumbers.size();

        if (sizeOfNumbers != sieOfDistinctNumbers) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    private static List<Integer> splitWinningNumbersByComma(String inputWinningNumbers) {
        return Arrays.stream(inputWinningNumbers.split(COMMA))
                .map(InputValidator::convertStringToInteger)
                .peek(InputValidator::validateNumberRange)
                .filter(InputValidator::isValidNumber)
                .collect(Collectors.toList());
    }

    private static void validateNumberRange(int number) {
        if (number < VALID_LOTTO_START_NUMBER || number > VALID_LOTTO_END_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    private static boolean isValidNumber(int number) {
        try {
            validateNumberRange(number);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static int convertStringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_MESSAGE);
        }
    }

    private static void validateEmpty(String input) {
        if (input.equals(EMPTY)) {
            throw new IllegalArgumentException(INVALID_EMPTY_INPUT_MESSAGE);
        }
    }

    private static void validateContainBlank(String input) {
        boolean containBlank = input.chars()
                .anyMatch(ch -> ch == BLANK);

        if (containBlank) {
            throw new IllegalArgumentException(INVALID_CONTAIN_BLANK_INPUT_MESSAGE);
        }
    }

    private static void validateRestZero(int buyAmount) {
        int rest = buyAmount % THOUSAND;

        if (rest != ZERO) {
            throw new IllegalArgumentException(INVALID_BUY_AMOUNT_MESSAGE);
        }
    }
}