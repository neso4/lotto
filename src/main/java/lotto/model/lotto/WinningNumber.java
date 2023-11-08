package lotto.model.lotto;

import static lotto.model.constants.StringConstant.DELIMITER;
import static lotto.validator.Validator.checkNumericWithCommaInput;
import static lotto.validator.Validator.numberMustBe1To45;

import java.util.Arrays;
import java.util.List;

public class WinningNumber {
    private final Lotto answer;
    public WinningNumber(String winningNumber) {
        checkNumericWithCommaInput(winningNumber);
        List<Integer> lotto
                = Arrays.stream(winningNumber.split(DELIMITER.get()))
                .map(Integer::parseInt)
                .toList();
        numberMustBe1To45(lotto);
        answer = new Lotto(lotto);
    }

    public boolean compareWinningNumberAndBonusNumber(int bonusNumber){
        return answer.isBonusNumberDuplicatedWithWinningNumber(bonusNumber);
    }

    public boolean isContainNumber(int number){
        return answer.isContainNumber(number);
    }

    @Override
    public boolean equals(Object expectedNumbers){
        return answer.equals(expectedNumbers);
    }
}
