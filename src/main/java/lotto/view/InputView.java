package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String ASK_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNER_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String ERROR_MESSAGE_ABOUT_PURCHASE_AMOUNT = "[ERROR] 올바른 값을 입력해주세요";
    private static final String ERROR_MESSAGE_ABOUT_DUPLICATED_LOTTO_NUMBERS = "[ERROR] 중복되지 않는 숫자를 입력해주세요.";
    private static final String ERROR_MESSAGE_ABOUT_WRONG_RANGED_LOTTO_NUMBERS = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String ERROR_MESSAGE_ABOUT_BLANK_INPUT = "[ERROR] 입력값이 없습니다.";
    private static final String COMMA = ",";
    private static final String ZERO = "0";
    public static int inputPurchaseAmount() {
        String input = "";
        try {
            System.out.println(ASK_PURCHASE_AMOUNT);
            input = Console.readLine();
            validateInputBlank(input);
            validateInputNumber(input);
        } catch(IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE_ABOUT_PURCHASE_AMOUNT);
        }
        return Integer.parseInt(input);
    }


    private static void validateInputBlank(String input) {
        if (input.replaceAll(" ", "").equals("")) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateInputNumber(String input) {
        if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
    }

    public static List inputWinnerNumbers() {
        String input = "";
        try {
            System.out.println(ASK_WINNER_NUMBERS);
            input = Console.readLine();
            validateInputBlank(input);
        } catch(IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE_ABOUT_BLANK_INPUT);
        }
        return convertStringToListWithDelimeter(input);
    }

    public static int inputBonusNumbers() {
        String input = "";
        try {
            System.out.println(ASK_BONUS_NUMBER);
            input = Console.readLine().replaceAll(" ", "");
            validateInputBlank(input);
        } catch(IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE_ABOUT_BLANK_INPUT);
        }
        return Integer.parseInt(input);
    }

    private static List<Integer> convertStringToListWithDelimeter(String input) {
        return Arrays.stream(input.replaceAll(" ", "").split(COMMA)).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

}
