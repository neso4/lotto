package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public long inputAmount() {
        String input = Console.readLine();
        validateAmount(input);
        return Long.parseLong(input);
    }

    public List<Integer> inputWinningNumbers() {
        String input = Console.readLine();
        return StringToIntConversion(input);
    }

    public static void validateAmount(String input) {
        validateStringToLongConversion(input);
    }

    private static void validateStringToLongConversion(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 입력이 필요합니다.");
        }
    }

    private List<Integer> StringToIntConversion(String input) {
        String[] stringWinningNumber = input.split(",");
        List<Integer> intWinningNumber = new ArrayList<>();

        for (String number : stringWinningNumber) {
            validateStringToLongConversion(number);
            intWinningNumber.add(Integer.parseInt(number));
        }
        return intWinningNumber;
    }
}