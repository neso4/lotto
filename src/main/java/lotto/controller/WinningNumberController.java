package lotto.controller;

import lotto.domain.lottery.BonusNumber;
import lotto.domain.lottery.Lotto;
import lotto.domain.validation.validator.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import static lotto.controller.LotteryGameController.*;
import static lotto.domain.validation.ExceptionMessage.*;

import java.util.ArrayList;
import java.util.List;

public class WinningNumberController {
    private static final String SPLIT_SYMBOL = ",";
    private final InputView inputView = new InputView();
    private final Validator validator = new Validator();

    void inputWinningNumberStage() {
        OutputView.requestWinningNumberMessage();
        requestWinningNumber();
        requestBonusNumber();
    }

    private void requestBonusNumber() {
        OutputView.printNewLine();
        OutputView.requestBonusNumberMessage();
        bonusNumber = new BonusNumber(returnBonusNumber());
    }

    private int returnBonusNumber() {
        String input = inputView.returnInput();

        if (!validator.isInputInteger(input)) {
            System.out.println(EXCEPTION_MESSAGE_PREFIX.getMessage() + BONUS_NUMBER_FORMAT_EXCEPTION.getMessage());
            return returnBonusNumber();
        }
        if (validateBonusNumber(input)) {
            return returnBonusNumber();
        }
        return Integer.parseInt(input);
    }

    private boolean validateBonusNumber(String input) {
        return !isInRange(input) || !isNotDuplicated(input);
    }

    private boolean isInRange(String input) {
        try {
            validator.checkBonusNumberInRange(input);
        } catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION_MESSAGE_PREFIX.getMessage() + BONUS_NUMBER_FORMAT_EXCEPTION.getMessage());
            return false;
        }
        return true;
    }

    private boolean isNotDuplicated(String input) {
        try {
            validator.checkWinningNumberContainsBonusNumber(winningLotto.getNumbers(), input);
        } catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION_MESSAGE_PREFIX.getMessage() + BONUS_NUMBER_DUPLICATES_EXCEPTION.getMessage());
            return false;
        }
        return true;
    }

    private void requestWinningNumber() {
        try {
            winningLotto = new Lotto(inputWinningNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION_MESSAGE_PREFIX.getMessage() + LOTTERY_NUMBER_FORMAT_EXCEPTION.getMessage());
            requestWinningNumber();
        }
    }

    private List<Integer> inputWinningNumber() {
        String input = inputView.returnInput();
        String[] numberStrings = input.split(SPLIT_SYMBOL);

        List<Integer> numbers = new ArrayList<>();
        for (String numberString : numberStrings) {
            if (validator.isInputInteger(numberString)) {
                numbers.add(Integer.parseInt(numberString));
                continue;
            }
            System.out.println(EXCEPTION_MESSAGE_PREFIX.getMessage() + LOTTERY_NUMBER_FORMAT_EXCEPTION.getMessage());
            return inputWinningNumber();
        }
        return numbers;
    }
}
