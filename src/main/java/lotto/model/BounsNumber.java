package lotto.model;

import static lotto.constant.ErrorMessage.ERROR_MESSAGE;
import static lotto.constant.ErrorMessage.LOTTONUMBERRANGEOVER;
import static lotto.constant.Numbers.LOTTONUMBEREND;
import static lotto.constant.Numbers.LOTTONUMBERSTART;

public class BounsNumber {
    private final Integer bonusNumber;

    public BounsNumber(Integer bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
    private void validate(Integer bonusNumber){
        validateBonusNumberRange(bonusNumber);
    }
    private void validateBonusNumberRange(Integer bonusNumber){
        if(bonusNumber > LOTTONUMBEREND.getIntValue() || bonusNumber < LOTTONUMBERSTART.getIntValue()){
            throw new IllegalArgumentException(ERROR_MESSAGE.getValue() + LOTTONUMBERRANGEOVER.getValue());
        }
    }
}
