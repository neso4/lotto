package lotto.view;

import static lotto.constants.Message.ASK_BONUS_NUMBER;
import static lotto.constants.Message.ASK_PURCHASE;
import static lotto.constants.Message.ASK_WINNING_NUMBER;

import lotto.validator.InputValidator;

public class InputView {
    private final InputDevice consoleInputDevice;

    public InputView(InputDevice consoleInputDevice) {
        this.consoleInputDevice = consoleInputDevice;
    }


    public String printAskPurchase() {
        System.out.println(ASK_PURCHASE.getMessage());
        String input = consoleInputDevice.getInput();
        InputValidator.validateIsInteger(input);
        return input;
    }

    public String askWinningNumbers() {
        System.out.println(ASK_WINNING_NUMBER.getMessage());
        return consoleInputDevice.getInput();
    }

    public String askBonusNumber() {
        System.out.println(ASK_BONUS_NUMBER.getMessage());
        return consoleInputDevice.getInput();
    }
}
