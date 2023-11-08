package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lotto.domain.Lotto;

public class InputView {
    private static final String TOTAL_COST = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String ZERO = "0";

    public static int totalCost() {
        System.out.println(TOTAL_COST);
        return parseToInt(input());
    }

    public static String winningNumber() {
        System.out.println(WINNING_NUMBER);
        return input();
    }

    public static int bonusNumber() {
        System.out.println();
        System.out.println(BONUS_NUMBER);
        return parseToInt(input());
    }

    private static String input() {
        String input = Console.readLine();
        isBlank(input);
        return input;
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    private static void isBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값을 입력해주세요.");
        }
    }

    private void isZero(String input) {
        if (Objects.equals(input, ZERO)) {
            throw new IllegalArgumentException("0을 제외한 정확한 입력값을 입력해주세요.");
        }
    }
}