package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigDecimal;
import lotto.message.ErrorMessage;
import lotto.message.OutputMessage;

public class LottoPurchaseInput {
    public static BigDecimal inputPurchaseAmount() {
        try {
            OutputMessage.ASK_PURCHASE_AMOUNT.printMessage();
            String input = Console.readLine();
            validate(input);
            return new BigDecimal(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static void validate(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CONTAINS_WHITE_CHAR.getMessage());
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_EMPTY.getMessage());
        }
    }
}
