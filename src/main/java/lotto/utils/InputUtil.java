package lotto.utils;

import camp.nextstep.edu.missionutils.Console;
import lotto.error.Error;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputUtil {
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int WINNING_START_NUMBER = 1;
    private static final int WINNING_END_NUMBER = 45;
    private static final String SPLIT_MARK = ",";

    public String inputPurchaseAmount() {
        while (true) {
            String input = Console.readLine();
            try {
                validateInteger(input);
                validateUnit(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputWinningNumber() {
        while (true) {
            String input = Console.readLine();
            String[] splitInputs = input.split(SPLIT_MARK);
            try {
                validateWinningNumber(splitInputs);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateWinningNumber(String[] inputs) {
        validateSize(inputs);
        for (String input : inputs) {
            validateInteger(input);
            validateRange(Integer.parseInt(input));
        }
        Set<String> inputSet = new HashSet<>(List.of(inputs));
        validateDuplication(inputSet, inputs);
    }


    private void validateInteger(String input) {
        // 숫자가 아닌 경우 예외 발생
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(Error.LOTTO_PURCHASE_AMOUNT_ERROR.getMessage());
        }
    }

    private void validateUnit(String input) {
        int purchaseAmount = Integer.parseInt(input);
        if (purchaseAmount%PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(Error.LOTTO_PURCHASE_AMOUNT_UNIT_ERROR.getMessage());
        }
    }

    private void validateDuplication(Set<String> inputSet, String[] inputs) {
        if (inputSet.size() != inputs.length) {
            throw new IllegalArgumentException(Error.WINNING_NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

    private void validateRange(int input) {
        if (WINNING_START_NUMBER > input || input > WINNING_END_NUMBER) {
            throw new IllegalArgumentException(Error.WINNING_NUMBER_RANGE_ERROR.getMessage());
        }
    }

    private void validateSize(String[] input) {
        if (input.length != 6) {
            throw new IllegalArgumentException(Error.WINNING_NUMBER_SIZE_ERROR.getMessage());
        }
    }
}
