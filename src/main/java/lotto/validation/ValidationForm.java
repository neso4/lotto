package lotto.validation;

import lotto.property.ValidationProperty;
import lotto.validation.validator.CostValidator;

import static lotto.property.ValidationProperty.*;
import static lotto.validation.validator.WinningValidator.*;
import static lotto.validation.validator.CostValidator.*;


public class ValidationForm {

    public static void verifyFormatForInputValue(ValidationProperty validatorType,String inputValue){
        if (validatorType.equals(WINNING)){
            verifyForWinningNumber(inputValue);
        }
        if (validatorType.equals(WINNINGS)){
            verifyForWinningNumbers(inputValue);
        }
        if (validatorType.equals(COST)){
            verifyForPurchaseCost(inputValue);
        }
    }

    static void verifyForWinningNumbers(String winningNumbers){
        winningsFormatIsCorrect(winningNumbers);
        winningsCountIsOverOrUnder(winningNumbers);
    }
    static void verifyForWinningNumber(String winningNumber){
        valueIsEmpty(winningNumber);
        valueContainsSpace(winningNumber);
        valueIsNumeric(winningNumber);
        winningOrBonusIsCorrectRange(winningNumber);
    }
    static void verifyForPurchaseCost(String purchaseCost){
        valueIsEmpty(purchaseCost);
        valueContainsSpace(purchaseCost);
        valueIsNumeric(purchaseCost);
        costIsStandardUnder(purchaseCost);
        costFormatIsCorrect(purchaseCost);
    }
}
