package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NUMBER_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public static void printEmptyLine() {
        System.out.println();
    }

    public static String inputPrice() {
        System.out.println(PRICE_INPUT_MESSAGE);
        return Console.readLine();
    }

    public static String inputMyLottoNumbers() {
        System.out.println(NUMBER_INPUT_MESSAGE);
        return Console.readLine();
    }

    public static String inputBonusNumber() {
        printEmptyLine();
        System.out.println(BONUS_INPUT_MESSAGE);
        return Console.readLine();
    }
}
