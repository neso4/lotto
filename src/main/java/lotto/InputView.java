package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String requestPrice() {
        System.out.println("구입금액을 입력해 주세요.");

        return reqeustInput();
    }

    public static String requestNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        return reqeustInput();
    }

    public static String requestBonus() {
        System.out.println("보너스 번호를 입력해 주세요.");

        return reqeustInput();
    }

    private static String reqeustInput() {
        return Console.readLine();
    }
}
