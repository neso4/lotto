package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigDecimal;
import java.util.List;
import lotto.data.Amount;
import lotto.data.Lotto;
import lotto.data.WinningCombination;
import lotto.message.ErrorMessage;
import lotto.utils.Util;

public class LottoPurchaseInput {
    private static String WINNING_NUMBER_SPLIT_CRITERIA= ",";
    public static Amount inputPurchaseAmount(BigDecimal lottoPrice) {
        try {
            LottoPurchaseOutput.ASK_PURCHASE_AMOUNT.printMessage();
            String input = Console.readLine().trim();

            validate(input);
            Util.validateNumber(input);

            return new Amount(input, lottoPrice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPurchaseAmount(lottoPrice);
        }
    }

    public static List<Integer> inputWinningNumbers() {
        try {
            LottoPurchaseOutput.ASK_WINNING_LOTTO_NUMBER.printMessage();
            String inputNumbers = Console.readLine().trim();

            validate(inputNumbers);
            Util.validateIntegerList(inputNumbers, WINNING_NUMBER_SPLIT_CRITERIA);
            List<Integer> numbers = Util.convertToIntegerList(inputNumbers, ",");
            Lotto.validate(numbers);

            return numbers;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    public static WinningCombination inputBonusNumber(List<Integer> winningNumbers) {
        try {
            LottoPurchaseOutput.ASK_BONUS_NUMBER.printMessage();
            String inputBonusNumber = Console.readLine().trim();

            validate(inputBonusNumber);
            Util.validateInteger(inputBonusNumber);
            int bonusNumber = Integer.parseInt(inputBonusNumber);
            WinningCombination winningCombination = new WinningCombination(winningNumbers, bonusNumber);

            return winningCombination;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBonusNumber(winningNumbers);
        }
    }

    private static void validate(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CONTAINS_WHITE_CHAR.getMessage());
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_EMPTY.getMessage());
        }
    }
}
