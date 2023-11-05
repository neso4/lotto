package lotto.domain;

import lotto.system.ExceptionMessage;
import lotto.system.SystemConstant;
import lotto.system.SystemMessage;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Money {
    private final long value;

    private Money(long value) {
        validate(value);
        this.value = value;
    }

    public static Money from(long value) {
        return new Money(value);
    }

    public static Money createManual() {
        OutputView.printFrom(SystemMessage.INPUT_MONEY);
        long userInput = SystemConstant.NOTHING.getValue();
        try {
            userInput = InputView.readLong();
        } catch (Exception e) {
            OutputView.exceptionMessage(e);
            createManual();
        }
        return new Money(userInput);
    }

    private void validate(long value) {
        validateDivisible(value);
        validateEnought(value);
    }

    private void validateDivisible(long value) {
        if (value % SystemConstant.LOTTO_TICKET_PRICE.getValue() != 0) {
            throw new IllegalArgumentException(ExceptionMessage.MONEY_NOT_DIVISIBLE.getMessage());
        }
    }

    private void validateEnought(long value) {
        if (value < SystemConstant.LOTTO_TICKET_PRICE.getValue()) {
            throw new IllegalArgumentException(ExceptionMessage.MONET_NOT_ENOUGH.getMessage());
        }
    }

    public int countPurchase() {
        return (int) (value / SystemConstant.LOTTO_TICKET_PRICE.getValue());
    }
}
