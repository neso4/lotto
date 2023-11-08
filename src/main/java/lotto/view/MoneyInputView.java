package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class MoneyInputView {
    private static final String HEADER = "구입금액을 입력해 주세요.";

    public static String readMoneyInput() {
        System.out.println(HEADER);
        return Console.readLine();
    }
}
