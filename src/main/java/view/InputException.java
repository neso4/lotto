package view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputException {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String CAN_BE_CONVERTED_TO_INTEGER_MESSAGE = "입력하신 값이 숫자가 아닙니다.";
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int ZERO = 0;
    private static final String IS_VALID_AMOUNT_MESSAGE = "로또 구입 금액은 1,000원 단위로 입력해야 합니다.";
    private static final String IS_NUMBER_IN_RANGE_MESSAGE = "로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final int WINNING_NUMBER_LENGTH = 6;
    private static final String HAS_SIX_NUMBERS_MESSAGE = "당첨 번호는 6개를 입력해야 합니다.";
    private static final String HAS_NO_DUPLICATE_NUMBERS_MESSAGE = "번호 중 중복되는 번호가 있습니다.";

    public static void canBeConvertedToInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE + CAN_BE_CONVERTED_TO_INTEGER_MESSAGE);
        }
    }

    public static void isValidAmount(String userInput) {
        int amount = Integer.parseInt(userInput);
        if (amount % PURCHASE_AMOUNT_UNIT > ZERO) {
            throw new IllegalArgumentException(ERROR_MESSAGE + IS_VALID_AMOUNT_MESSAGE);
        }
    }

    public static void isNumberInRange(String userInput) {
        int winningNum = Integer.parseInt(userInput);
        if (winningNum < 1 || winningNum > 45) {
            throw new IllegalArgumentException(ERROR_MESSAGE + IS_NUMBER_IN_RANGE_MESSAGE);
        }
    }

    public static void hasSixNumbers(String[] userInputs) {
        if (userInputs.length != WINNING_NUMBER_LENGTH) {
            throw new IllegalArgumentException(ERROR_MESSAGE + HAS_SIX_NUMBERS_MESSAGE);
        }
    }

    public static void hasNoDuplicateNumbers(List<String> userInputs) {
        Set<String> winningNums = new HashSet<>();

        userInputs.stream()
                .filter(winningNum -> !winningNums.add(winningNum))
                .findAny()
                .ifPresent(pNum -> {
                    throw new IllegalArgumentException(ERROR_MESSAGE + HAS_NO_DUPLICATE_NUMBERS_MESSAGE);
                });
    }
}
