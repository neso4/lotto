package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.constant.Number.THOUSAND;
import static lotto.constant.Number.ZERO;
import static lotto.constant.message.InputMessage.*;
import static lotto.constant.message.ErrorMessage.*;

public class InputMoneyView {
    private int money;

    public int inputMoney() {
        while (true) {
            try {
                System.out.println(MONEY_MESSAGE);
                money = Integer.parseInt(readLine());
                illegalArgument();
                break;
            } catch (NumberFormatException e) {
                System.out.println(IS_NOT_INTEGER);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return money;
    }

    private void illegalArgument() {
        if (money < THOUSAND) {
            throw new IllegalArgumentException(IS_UNDER_THOUSAND);
        }
        if (money % THOUSAND != ZERO) {
            throw new IllegalArgumentException(CAN_NOT_DIVIDE_BY_THOUSAND);
        }
    }
}
