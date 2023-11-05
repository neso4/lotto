package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String REQUEST_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBER = "\n당첨 번호를 입력해 주세요.";

    public String getPurchaseAmountInput() {
        System.out.println(REQUEST_PURCHASE_AMOUNT);
        String input = Console.readLine();
        Validator.validateIsBlank(input);
        Validator.validateIsInteger(input);
        return input;
    }

    public String getWinningNumberInput() {
        System.out.println(REQUEST_WINNING_NUMBER);
        String input = Console.readLine();
        Validator.validateIsBlank(input);
        return input;
    }
}