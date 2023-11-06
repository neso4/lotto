package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String DELIMITER = ",";

    public static int readPayMoney() {
        System.out.println(ViewMessage.INPUT_MONEY.getMessage());
        String input = Console.readLine();
        System.out.println();
        return Integer.parseInt(input);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println(ViewMessage.INPUT_WINNING_NUMBERS.getMessage());
        String input = Console.readLine();
        System.out.println();
        return Arrays.stream(input.split(DELIMITER)).map(Integer::parseInt).toList();
    }

    public static int readBonusNumber() {
        System.out.println(ViewMessage.INPUT_BONUS_NUMBER.getMessage());
        String input = Console.readLine();
        System.out.println();
        return Integer.parseInt(input);
    }
}
