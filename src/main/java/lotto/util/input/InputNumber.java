package lotto.util.input;

import static lotto.util.exception.ErrorMessage.BLANK_INPUT;
import static lotto.util.exception.ErrorMessage.NOT_INTEGER;

import lotto.util.exception.LottoException;

public abstract class InputNumber implements InputValidator {
    protected final Integer number;

    protected InputNumber(String input) {
        checkBlank(input);
        number = isInteger(input);
        validate();
    }

    private void checkBlank(String input) {
        input = input.replaceAll(" ", "");
        if (input.isBlank()) {
            throw LottoException.of(BLANK_INPUT);
        }
    }

    private Integer isInteger(String input) {
        Integer number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw LottoException.of(NOT_INTEGER);
        }

        return number;
    }

    public abstract void validate();
}
