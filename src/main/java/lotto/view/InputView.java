package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";

    public static String readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        return Console.readLine();
    }

    public static String readWinningNumbers() {
        System.out.println();
        System.out.println(WINNING_NUMBER_MESSAGE);
        return Console.readLine();
    }
}
