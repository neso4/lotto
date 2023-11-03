package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER = "당첨번호를 입력해 주세요.";
    private static final String BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static String purchaseMoney(){
        System.out.println(PURCHASE_MONEY);
        return Console.readLine();
    }

    public static String winningNumber(){
        System.out.println(WINNING_NUMBER);
        return Console.readLine();
    }

    public static String bonusNumber(){
        System.out.println(BONUS_NUMBER);
        return Console.readLine();
    }

}
