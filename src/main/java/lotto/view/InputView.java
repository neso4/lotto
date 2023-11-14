package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.InputValidator;
import lotto.util.WinningLottoValidator;

import java.util.List;

import static lotto.message.RequestMessage.*;

public class InputView {

    public Integer requestPurchaseAmountInput(){
        System.out.println(REQUEST_PURCHASE_AMOUNT_MESSAGE.getMessage());
        String playerInput = Console.readLine();
        return InputValidator.validatePurchaseAmount(playerInput);
    }

    public List<Integer> requestWinningNumbersInput(){
        System.out.println(REQUEST_WINNING_NUMBERS_MESSAGE.getMessage());
        String playerInput = Console.readLine();
        List<Integer> winningNumbers = InputValidator.validateWinningNumbers(playerInput);
        WinningLottoValidator.validateLotto(winningNumbers);
        return winningNumbers;
    }

    public Integer requestBonusNumberInput(){
        System.out.println(REQUEST_BONUS_NUMBER_MESSAGE.getMessage());
        String playerInput = Console.readLine();
        Integer bonusNumber = InputValidator.validateBonusNumber(playerInput);
        WinningLottoValidator.validateLottoNumberOutOfRange(bonusNumber);
        return bonusNumber;
    }
}
