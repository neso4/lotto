package lotto.domain;

import java.util.List;

public class BonusNumber {

    public void validateBonusNumber(List<Integer> answerNumber, int bonusNumber) {
        validateBonusNumberSize(bonusNumber);
        validateDuplicateBonusNumber(answerNumber, bonusNumber);
    }

    public void validateDuplicateBonusNumber(List<Integer> answerNumber, int bonusNumber) {
        if(answerNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] : 정답 번호와 동일한 보너스 번호가 있으면 안됩니다.");
        }
    }

    public void validateBonusNumberSize(int bonusNumber) {
        if(bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] : 보너스 번호는 1~45 사이 입니다.");
        }
    }
}
