package lotto.view;

import static lotto.util.ConstantUtils.*;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.util.ValidationUtils;

public class InputView {

    public static int readPurchaseAmount() {
        OutputView.printLottoPurchaseAmountPrompt();
        String input = Console.readLine();
        ValidationUtils.validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    public static List<Integer> readWinningNumbers() {
        OutputView.printWinningNumbersPrompt();
        String input = Console.readLine();
        validateNumbersInput(input);
        List<Integer> winningNumbers = Arrays.stream(input.split(DELIMITER))
            .map(Integer::parseInt)
            .toList();
        validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    public static int readBonusNumber() {
        OutputView.printBonusNumberPrompt();
        String input = Console.readLine();
        validateBonusNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateBonusNumber(String input) {
        ValidationUtils.validateNotNull(input);
        ValidationUtils.validateIsNumeric(input);
        ValidationUtils.validateNumberInRange(Integer.parseInt(input), LOTTO_START_INCLUSIVE,
            LOTTO_END_INCLUSIVE);
    }

    private static void validateWinningNumbers(List<Integer> winningNumbers) {
        ValidationUtils.validateNumbersSize(winningNumbers);
        ValidationUtils.validateNoDuplicatedNumberInList(winningNumbers);
        ValidationUtils.validateNumbersInRange(winningNumbers, LOTTO_START_INCLUSIVE,
            LOTTO_END_INCLUSIVE);
    }

    private static void validateNumbersInput(String input) {
        ValidationUtils.validateNotNull(input);
        validateNotStartOrEndWithComma(input);
        validateNoConsecutiveCommas(input);
        validateNoCommasWithSpaces(input);
        validateNoSpecialCharactersExceptComma(input);
    }

    private static void validateNotStartOrEndWithComma(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력의 시작 혹은 끝에 쉼표가 있습니다.");
        }
    }

    private static void validateNoConsecutiveCommas(String input) {
        if (input.contains(DELIMITER.repeat(2))) {
            throw new IllegalArgumentException("쉼표가 연속해서 있습니다.");
        }
    }

    private static void validateNoCommasWithSpaces(String input) {
        if (input.contains(SPACE + DELIMITER) || input.contains(DELIMITER + SPACE)) {
            throw new IllegalArgumentException("쉼표 옆에 공백이 있습니다.");
        }
    }

    private static void validateNoSpecialCharactersExceptComma(String input) {
        if (input.matches(".*[^0-9,\\s]+.*")) {
            throw new IllegalArgumentException("쉼표를 제외한 특수문자가 있습니다.");
        }
    }
}
