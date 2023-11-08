package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.validation.InputValidation;

public class InputView {

    private final InputValidation inputValidation;

    public InputView(InputValidation inputValidation) {
        this.inputValidation = inputValidation;
    }

    public int inputPayment() {
        while (true) {
            try {
                String input = Console.readLine();
                inputValidation.validatePayment(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException error) {
                System.out.println(error);
            }
        }
    }

    public List<Integer> inputWinningNumbers() {
        while (true) {
            try {
                String input = Console.readLine();
                inputValidation.validateWinningNumbers(input);
                String[] inputNumbers = input.split(",");
                return Arrays.stream(inputNumbers).map(Integer::parseInt).toList();
            } catch (IllegalArgumentException error) {
                System.out.println(error);
            }
        }
    }

    public int inputBounusNumber() {
        while (true) {
            try {
                String input = Console.readLine();
                inputValidation.validateBonusNumber(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException error) {
                System.out.println(error);
            }
        }
    }
}
