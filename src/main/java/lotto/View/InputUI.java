package lotto.View;

import lotto.View.InputValidators.BonusNumberValidator;
import lotto.View.InputValidators.PurchasePriceValidator;
import lotto.View.InputValidators.WinningNumberValidator;
import java.util.List;

public class InputUI {
    public static int inputPurchasePrice() throws IllegalArgumentException{
        // PurchasePriceValidator inherits InputValidator, so constructing it includes input process
        PurchasePriceValidator purchasePriceValidator = new PurchasePriceValidator();
        return purchasePriceValidator.returnValidatedPrice();
    }
    public static List<Integer> inputWinningNumber() throws IllegalArgumentException{
        // WinningNumberValidator inherits InputValidator, so constructing it includes input process
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return winningNumberValidator.returnValidatedWinningNumber();
    }
    public static int inputBonusNumber(List<Integer> winningNumber) throws IllegalArgumentException{
        // BonusNumberValidator inherits InputValidator, so constructing it includes input process
        BonusNumberValidator bonusNumberValidator = new BonusNumberValidator(winningNumber);
        return bonusNumberValidator.returnValidatedBonusNumber();
    }
}
