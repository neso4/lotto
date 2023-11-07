package lotto.validator;

import lotto.domain.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumberValidator {

    Constants constants = new Constants();

    public void checkLottoNumber(List<String> lotto) {
        checkLottoNumberIsNumber(lotto);
        checkLottoInRange(lotto);
        checkLottoDuplicate(lotto);
    }

    public void checkBonusNumber(List<Integer> lotto, String bonusNumber) {
        // 숫자인지 validate하는 함수

        checkBonusDuplicate(lotto, Integer.parseInt(bonusNumber));
    }

    public void checkLottoNumberIsNumber(List<String> lotto) {
        for (int i = 0; i < lotto.size(); i++) {
            String lottoNumber = lotto.get(i);
            try{
                Integer.parseInt(lottoNumber);
            }
            catch(NumberFormatException e) {
                System.out.printf(constants.LOTTO_NUMBER_NOT_NUMBER_ERROR);
                throw new IllegalArgumentException();
            }
        }
    }

    // 로또 번호가 중복이 없는지 확인
    public void checkLottoDuplicate(List<String> lotto) {
        Set<String> lottoWithoutDuplicate = new HashSet<>(lotto);
        if (lotto.size() != lottoWithoutDuplicate.size()) {
            System.out.printf(constants.WINNING_NUMBER_DUPLICATED_ERROR);
            throw new IllegalArgumentException();
        }
    }

    public void checkLottoInRange(List<String> lotto) {
        for (int i=constants.ZERO_NUMBER; i<lotto.size(); i++) {
            int lottoNumber = Integer.parseInt(lotto.get(i));
            if(lottoNumber >= constants.LOTTO_NUMBER_MIN && lottoNumber <= constants.LOTTO_NUMBER_MAX) {
                continue;
            }
            System.out.printf(constants.LOTTO_NUMBER_RANGE_ERROR);
            throw new IllegalArgumentException();
        }
    }

    // 보너스넘버가 중복이 아닌지 확인
    public void checkBonusDuplicate(List<Integer> lotto, int bonusNumber) {
        for (int i = 0; i < constants.LOTTO_NUMBER; i++) {
            if(lotto.get(i) == bonusNumber) {
                System.out.printf(constants.BONUS_NUMBER_DUPLICATED_ERROR);
                throw new IllegalArgumentException();
            }
        }
    }
}
