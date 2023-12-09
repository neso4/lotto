package lotto.global.view;

import static lotto.global.enums.ErrorMessage.NOT_NUMBER_INPUT_ERROR_MESSAGE;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.global.utils.ExceptionHandlingUtil;

public class InputView {

    private static final String ENTER = System.lineSeparator();
    private static final String BUY_LOTTO_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_MESSAGE = ENTER + "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = ENTER + "보너스 번호를 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ",";


    private InputView() {
    }

    private static int getInt() {
        try {
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    private static List<Integer> getNumbers() {
        try {
            String input = Console.readLine();
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    public static int getPurchaseMoney() {
        return ExceptionHandlingUtil.handleExceptionWithMessage(
                message -> System.out.println(BUY_LOTTO_MESSAGE),
                InputView::getInt
        );
    }

    public static List<Integer> getWinningNumbersFromInput() {
        return ExceptionHandlingUtil.handleExceptionWithMessage(
                message -> System.out.println(WINNING_NUMBERS_MESSAGE),
                InputView::getNumbers
        );
    }

    public static int getBonusNumber() {
        return ExceptionHandlingUtil.handleExceptionWithMessage(
                message -> System.out.println(BONUS_NUMBER_MESSAGE),
                InputView::getInt
        );
    }


}
