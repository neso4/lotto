package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.InvalidNumberFormatException;
import lotto.validation.ValidateNumberFormat;
import lotto.validation.ValidateThousandWonFormat;

import java.util.List;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";

    public void readInputMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public void readInputWinningNumberMessage() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
    }

    public int inputMoney() {
        String inputMoney;
        while (true) {
            inputMoney = Console.readLine();
            try {
                validateInputMoney(inputMoney);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Integer.parseInt(inputMoney)/1000;
    }

    private void validateInputMoney(String inputMoney) {
        ValidateNumberFormat.validate(inputMoney);
        ValidateThousandWonFormat.validate(inputMoney);
    }

    public void inputWinningNumber() {
        String inputWinnigNumber;
        inputWinnigNumber = Console.readLine();
    }
}
