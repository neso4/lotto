package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.view.constant.InputMessage.*;

public class InputView {
    private InputView() {}
    private static class InputViewHelper {
        private final static InputView INPUT_VIEW = new InputView();
    }
    public static InputView getInstance() {
        return InputViewHelper.INPUT_VIEW;
    }

    public String inputPrice() {
        System.out.println(PRICE.getMessage());
        return Console.readLine();
    }

    public String inputWinningNumbers() {
        System.out.println(WINNING_NUMBERS.getMessage());
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(BONUS_NUMBER.getMessage());
        return Console.readLine();
    }
}
