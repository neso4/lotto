package lotto.generator;

import lotto.domain.model.BonusNumber;
import lotto.domain.model.WinningNumber;
import lotto.domain.validator.Validator;
import lotto.view.InputView;

public class BonusNumberGenerator {

    public BonusNumber run(WinningNumber winningNumber) {
        int bonusNumber = drawBonusNumber();

        try {
            winningNumber.validateAlreadyHaveNumber(bonusNumber);
             BonusNumber validateBonusNumber = new BonusNumber(bonusNumber);

            return validateBonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return this.run(winningNumber);
        }
    }

    private int drawBonusNumber() {
        String inputBonusValue = InputView.inputBonusNumber();

        try {
            Validator.validateExistValue(inputBonusValue);
            int convertedBonusNum = Validator.validateNumericInput(inputBonusValue);
            int bonusNumberInRange = Validator.validateNumberInRange(convertedBonusNum);

            return bonusNumberInRange;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return this.drawBonusNumber();
        }
    }
}
