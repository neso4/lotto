package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constant.PrintMessages;
import lotto.validators.InputValidator;

public class InputHandler {
    public static String purchasePrice() {
        System.out.println(PrintMessages.INPUT_PRICE);
        String priceInput;

        while (true) {
            try {
                priceInput = Console.readLine();
                InputValidator.validatePriceInput(priceInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return priceInput;
    }

    public static String winningNumber() {
        System.out.println(PrintMessages.INPUT_WINNING_NUMBER);
        return Console.readLine();
    }

    public static String bonusNumber() {
        System.out.println(PrintMessages.INPUT_BONUS_NUMBER);
        return Console.readLine();
    }
}
