package lotto.io.views;

import lotto.io.Input;
import lotto.io.Output;

public class LottoGameView {

    public static final String ERROR_HEADER_MESSAGE = "[ERROR]";

    public int askPurchaseAmount() {
        while (true) {
            Output.consoleLine("구입금액을 입력해 주세요.");
            try {
                return Input.consoleNumber();
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_HEADER_MESSAGE + e.getMessage());
            }
        }
    }

}
