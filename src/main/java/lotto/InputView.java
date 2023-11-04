package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String PURCHASE_MESSAGE = "구입금액을 입력해 주세요";
    private final static String WINNING_NUMBER_PROMPT = "당첨 번호를 입력해 주세요.";

    public static String getPurchaseAmount() {
        System.out.println(PURCHASE_MESSAGE);
        return Console.readLine();
    }

    public static String get_winning_number(){
        System.out.println(WINNING_NUMBER_PROMPT);
        return Console.readLine();
    }
}
