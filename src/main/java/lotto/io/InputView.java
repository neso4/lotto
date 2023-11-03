package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String inputLottoPurchasePrice() {
        String lottoPurchasePrice = Console.readLine();
        inputValidator.validateNumeric(lottoPurchasePrice);
        return lottoPurchasePrice;
    }
}
