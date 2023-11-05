package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    private static final String REQUEST_COST = "구입금액을 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";

    public String buyLotto() {
        System.out.println(REQUEST_COST);

        return Console.readLine();
    }

    public String winningNumbers() {
        System.out.println(REQUEST_WINNING_NUMBER);

        return Console.readLine();
    }
}
