package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static lotto.utils.Parser.parseToInteger;
import static lotto.utils.Parser.parseToIntegers;

public class InputView {
    private static final String INPUT_BUY_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WIN_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "\n+보너스 번호를 입력해 주세요.";

    public static Integer inputPurchaseAmount() {
        System.out.println(INPUT_BUY_MONEY_MESSAGE);
        String input = Console.readLine();
        InputValidator.validatePurchaseAmount(input);
        return parseToInteger(input);
    }

    public static List<Integer> inputWinNumbers() {
        System.out.println(INPUT_WIN_NUMBERS_MESSAGE);
        String input = Console.readLine();
        InputValidator.validateWinNumbers(input);
        return parseToIntegers(input);
    }

    public static Integer inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String input = Console.readLine();
        InputValidator.validateBonusNumber(input);
        return parseToInteger(input);
    }
}
