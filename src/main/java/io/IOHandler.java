package io;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

import static io.IOMessages.LOTTO_PURCHASE_AMOUNT_MESSAGE;
import static io.IOMessages.WINNING_NUMBERS_MESSAGE;
import static io.ValidationErrorMessages.NOT_INTEGER;

public class IOHandler {

    private final InputValidator validator = new InputValidator();

    public int inputLottoPurchaseAmount() {
        int lottoPurchaseAmount;
        System.out.println(LOTTO_PURCHASE_AMOUNT_MESSAGE.getMessage());

        try {
            lottoPurchaseAmount = Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getMessage());
        }

        validator.validateLottoPurchaseAmount(lottoPurchaseAmount);

        return lottoPurchaseAmount;
    }

    public List<Integer> inputWinningNumbers() {
        List<Integer> winningNumbers;
        System.out.println(WINNING_NUMBERS_MESSAGE.getMessage());

        try {
            String input = Console.readLine();
            winningNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getMessage());
        }

        validator.validateWinningNumbers(winningNumbers);

        return winningNumbers;
    }
}
