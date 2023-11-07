package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoGameMessage;

public class InputView {
    private static final String DELIMITER = ",";

    public long validateCustomerMoneyInput(final String moneyInput) {
        if (moneyInput.isEmpty()) {
            throw new NumberFormatException(ErrorMessage.EMPTY.errorMessage);
        }

        moneyInput.chars().forEach(o -> {
            if (!Character.isDigit(o)) {
                throw new NumberFormatException(ErrorMessage.NOT_DIGIT_MONEY.errorMessage);
            }
        });

        return Long.parseLong(moneyInput);
    }

    public String[] validateWinningNumberInput(final String winningNumberInput) {
        if (winningNumberInput.isEmpty()) {
            throw new NumberFormatException(ErrorMessage.EMPTY.errorMessage);
        }
        if (!winningNumberInput.contains(",")) {
            throw new NumberFormatException(ErrorMessage.DELIMITER.errorMessage);
        }

        String[] splitByDelimiter = this.validateEachWinningNumberInput(winningNumberInput);

        return splitByDelimiter;
    }

    public String[] validateEachWinningNumberInput(final String winningNumberInput) {
        String[] splitByDelimiter = winningNumberInput.split(DELIMITER);

        for (String eachWinningNumber : splitByDelimiter) {
            if (eachWinningNumber.chars().anyMatch(c -> !Character.isDigit((char) c))) {
                throw new NumberFormatException(ErrorMessage.NOT_DIGIT_WINNING_NUM.errorMessage);
            }
        }

        return splitByDelimiter;
    }

    public int validateBonusNumberInput(final String bonusNumberInput) {
        if (bonusNumberInput.isEmpty()) {
            throw new NumberFormatException(ErrorMessage.EMPTY.errorMessage);
        }
        if (bonusNumberInput.chars().anyMatch(c -> !Character.isDigit((char) c))) {
            throw new NumberFormatException(ErrorMessage.NOT_DIGIT_BONUS_NUM.errorMessage);
        }

        int bonusNumber = Integer.parseInt(bonusNumberInput);

        return bonusNumber;
    }

    public String requestLottoMoneyToBuy() {
        System.out.println(LottoGameMessage.BUY_LOTTO_MONEY.message);
        String moneyInput = Console.readLine();

        return moneyInput;
    }

    public String requestWinningNumber() {
        System.out.println(LottoGameMessage.REQUEST_WINNING_NUMBER.message);
        String winningNumberInput = Console.readLine();

        return winningNumberInput;
    }

    public String requestBonusNumber() {
        System.out.println(LottoGameMessage.REQUEST_BONUS_NUMBER.message);
        String bonusNumberInput = Console.readLine();

        return bonusNumberInput;
    }
}
