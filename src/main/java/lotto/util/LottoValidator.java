package lotto.util;

import static lotto.util.constant.ValidatorStatus.DUPLICATE_BONUS_NUMBER_INPUT;
import static lotto.util.constant.ValidatorStatus.DUPLICATE_WINNING_NUMBER;
import static lotto.util.constant.ValidatorStatus.INVALID_NUMBER_RANGE;
import static lotto.util.constant.ValidatorStatus.INVALID_WINNING_NUMBER_COUNT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.model.domain.LottoNumber;
import lotto.util.exception.ExceptionHandler;

public class LottoValidator {

    private final ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();

    public void validateLottoNumbers(List<Integer> lottoNumberInput) {
        isValidNumberCount(lottoNumberInput);
        isDuplicateLottoNumber(lottoNumberInput);
        isValidLottoNumberRange(lottoNumberInput);
    }

    public void validateLottoBonusNumber(List<LottoNumber> lottoNumbers, int bonusNumberInput) {
        isValidNumberRange(bonusNumberInput);
        isDuplicateBonusNumber(lottoNumbers, bonusNumberInput);
    }

    private void isValidNumberCount(List<Integer> lottoNumberInput) {
        if (lottoNumberInput.size() != 6) {
            exceptionHandler.validatorStatusMap.get(INVALID_WINNING_NUMBER_COUNT).accept(INVALID_WINNING_NUMBER_COUNT);
        }
    }

    private void isDuplicateLottoNumber(List<Integer> lottoNumberInput) {
        Set<Integer> formatCheck = new HashSet<>(lottoNumberInput);
        if (lottoNumberInput.size() != formatCheck.size()) {
            exceptionHandler.validatorStatusMap.get(DUPLICATE_WINNING_NUMBER)
                    .accept(DUPLICATE_WINNING_NUMBER);
        }
    }

    private void isValidLottoNumberRange(List<Integer> lottoNumberInput) {
        lottoNumberInput.forEach(this::isValidNumberRange);
    }

    private void isValidNumberRange(int lottoNumber) {
        if (lottoNumber < 0 || 45 < lottoNumber) {
            exceptionHandler.validatorStatusMap.get(INVALID_NUMBER_RANGE)
                    .accept(INVALID_NUMBER_RANGE);
        }
    }

    private void isDuplicateBonusNumber(List<LottoNumber> lottoNumbers, int bonusNumberInput) {
        boolean isContainBonusNumberInput = lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.number() == bonusNumberInput);
        if (isContainBonusNumberInput) {
            exceptionHandler.validatorStatusMap.get(DUPLICATE_BONUS_NUMBER_INPUT)
                    .accept(DUPLICATE_BONUS_NUMBER_INPUT);
        }
    }

}
