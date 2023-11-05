package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.service.LottoService;
import lotto.validation.InputValidation;

public class InputView {

    public static int inputPurchaseAmount() {
        String input = inputNotBlank();
        InputValidation.validateInputString(input);
        int purchaseInput = Integer.parseInt(input);
        InputValidation.validateInputPurchaseAmount(purchaseInput);
        return LottoService.amountPurchaseDivide(purchaseInput);
    }

    public static List<Integer> inputWinningNum() {
        String input = inputNotBlank();
        List<Integer> winNumberList = InputValidation.validateInputWinningNumber(input);
        InputValidation.validateNoDuplicates(winNumberList);
        InputValidation.validateNumberRange(winNumberList);
        InputValidation.validateSixNumbers(winNumberList);
        return winNumberList;
    }

    public static int inputBonusNum() {
        String input = inputNotBlank();
        InputValidation.validateInputString(input);
        int bonusNumber = InputValidation.validateBonusNumberRange(input);
        return bonusNumber;
    }

    private static String inputNotBlank() {
        String input = Console.readLine();
        InputValidation.validateIsBlank(input);
        return input;
    }
}
