package view;

import static util.ErrorMessage.ONLY_CAN_NUMBER;
import static util.ProgressMessage.INPUT_AMOUNT;

import camp.nextstep.edu.missionutils.Console;
import domain.Amount;

public class InputView {
    public static Amount inputLottoPurchaseAmount() {
        System.out.println(INPUT_AMOUNT.getProgressMessage());
        return new Amount(validateIsNumber());
    }

    private static int validateIsNumber(){
        while(true) {
            int amount = 0;
            try {
                amount = Integer.parseInt(getInput());
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(ONLY_CAN_NUMBER.getErrorMessage());
            }
        }
    }

    private static String getInput() {
        return Console.readLine();
    }

}
