package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class InputView {
    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();

        validatePurchaseAmountInput(input);

        return Integer.parseInt(input);
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        validateWinningNumbersInput(input);

        return null;
    }

    private static void validatePurchaseAmountInput(String input) {
        if (isNotPositiveInteger(input)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateWinningNumbersInput(String input) {
        String[] numbers = input.split(",");
        int lastIndex = numbers.length - 1;

        for (int i = 0; i < numbers.length; i++) {
            String numberStr = numbers[i].trim();

            if (!isNotPositiveInteger(numberStr)) {
                throw new IllegalArgumentException();
            }

            if (i < lastIndex && numberStr.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }

        if (numbers[lastIndex].isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static int toInteger(String input) {
        return Integer.parseInt(input);
    }

    private static boolean isNotPositiveInteger(String input) {
        try {
            return toInteger(input) <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
