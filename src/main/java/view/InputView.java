package view;

import static util.ErrorMessage.ONLY_CAN_NUMBER;
import static util.ProgressMessage.INPUT_AMOUNT;
import static util.ProgressMessage.INPUT_BONUS_NUMBER;
import static util.ProgressMessage.INPUT_WINNING_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import domain.Amount;
import domain.Lotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public static int inputLottoPurchaseAmount() {
        System.out.println(INPUT_AMOUNT.getProgressMessage());
        return validateIsNumber();
    }

    private static int validateIsNumber(){
        while(true) {
            int number = 0;
            try {
                number = Integer.parseInt(getInput());
                return number;
            } catch (IllegalArgumentException e) {
                System.out.println(ONLY_CAN_NUMBER.getErrorMessage());
            }
        }
    }

    public static Lotto inputWinningLottoNumber() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBER.getProgressMessage());
        return new Lotto(validateIsLotto());
    }

    private static List<Integer> validateIsLotto(){
        while(true) {
            List<Integer> winningNumbers = new ArrayList<>();
            try {
                List<String> input = Arrays.stream(getInput().split(","))
                        .toList();
                input.forEach(winningnumber -> {
                    winningNumbers.add(Integer.parseInt(winningnumber));
                });
                return winningNumbers;
            } catch (IllegalArgumentException e) {
                System.out.println(ONLY_CAN_NUMBER.getErrorMessage());
            }
        }
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER.getProgressMessage());
        return validateIsNumber();
    }

    private static String getInput() {
        return Console.readLine();
    }

}
