package lotto.model;

import lotto.ErrorMessage;

public class InputBonusNumber {

    private final int number;

    public InputBonusNumber(String inputNumber) {
        validate(inputNumber);
        this.number = parseNumberToInt(inputNumber);
    }

    public int getNumber() {
        return number;
    }

    private int parseNumberToInt(String inputNumber) {
        return Integer.parseInt(inputNumber);
    }

    private static void validate(String inputNumber) {
        int number = Integer.parseInt(inputNumber);
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE.getMessage());
        }
    }
}
