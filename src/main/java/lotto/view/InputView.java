package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Stream;
import lotto.ErrorLogger;
import lotto.model.Lotto;

public class InputView {
    private static final String PURCHASE_AMOUNT_INPUT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_PROMPT = "\n보너스 번호를 입력해 주세요.";

    public int readPurchaseAmount() {
        try {
            System.out.println(PURCHASE_AMOUNT_INPUT_PROMPT);
            String input = Console.readLine();
            int purchaseAmount = InputValidator.isNumberFormat(input);
            InputValidator.greaterThanThousand(purchaseAmount);
            InputValidator.divisibleByThousand(purchaseAmount);
            return purchaseAmount;
        } catch (IllegalArgumentException e) {
            ErrorLogger.log(e);
            return readPurchaseAmount();
        }
    }

    public Lotto readWinningNumbers() {
        try {
            System.out.println(WINNING_NUMBERS_INPUT_PROMPT);
            String input = Console.readLine();
            List<Integer> winningNumbers = Stream.of(input.split(","))
                    .map(Integer::parseInt)
                    .toList();
            return new Lotto(winningNumbers);
        } catch (IllegalArgumentException e) {
            ErrorLogger.log(e);
            return readWinningNumbers();
        }
    }

    public int readBonusNumber() {
        try {
            System.out.println(BONUS_NUMBER_INPUT_PROMPT);
            String input = Console.readLine();
            int bonusNumber = InputValidator.isNumberFormat(input);
            InputValidator.inRange(bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            ErrorLogger.log(e);
            return readBonusNumber();
        }
    }


}
