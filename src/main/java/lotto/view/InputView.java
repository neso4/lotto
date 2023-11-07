package lotto.view;


import camp.nextstep.edu.missionutils.Console;
import lotto.message.ConsoleMessage;

public class InputView {

    public static int getPurchaseMoney() {
        System.out.println(ConsoleMessage.GET_PURCHASE_MONEY.getMessage());
        return validateNumber(Console.readLine());
    }


    public static int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ConsoleMessage.LOTTO_NUMBER_RANGE_ERROR.getMessage());
        }
    }
}
