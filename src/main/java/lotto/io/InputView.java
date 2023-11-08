package lotto.io;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readNumber() {
        final String input = Console.readLine();
        inputValidator.validateNumeric(input);
        return input;
    }

    public String readWinningNumbers() {
        final String input = Console.readLine();
        inputValidator.validateWinningNumbers(input);
        return input;
    }
}
