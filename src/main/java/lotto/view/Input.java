package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import lotto.constant.OutputMessage;

public class Input {
    public String inputCost() {
        System.out.println(OutputMessage.ENTER_AMOUNT_MESSAGE);

        String cost = Console.readLine();

        System.out.println();

        return cost;
    }

    public String inputLotto() {
        System.out.println(OutputMessage.ENTER_WINNING_NUMBERS_MESSAGE);

        String lotto = Console.readLine();

        System.out.println();

        return lotto;
    }

    public String inputBonus() {
        System.out.println(OutputMessage.ENTER_BONUS_NUMBER_MESSAGE);

        String bonus = Console.readLine();

        System.out.println();

        return bonus;
    }
}
