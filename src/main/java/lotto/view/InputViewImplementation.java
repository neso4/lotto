package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.enumerate.Message.PLEASE_INPUT_AMOUNT;
import static lotto.enumerate.Message.PLEASE_INPUT_BONUS_NUMBER;
import static lotto.enumerate.Message.PLEASE_INPUT_WINNING_NUMBER;

public class InputViewImplementation implements InputView {
    @Override
    public String inputAmount() {
        System.out.println(PLEASE_INPUT_AMOUNT.getMessage());
        return readLine();
    }

    @Override
    public String inputWinningNumber() {
        System.out.println();
        System.out.println(PLEASE_INPUT_WINNING_NUMBER.getMessage());
        return readLine();
    }

    @Override
    public String inputBonusNumber() {
        System.out.println();
        System.out.println(PLEASE_INPUT_BONUS_NUMBER.getMessage());
        return readLine();
    }
}
