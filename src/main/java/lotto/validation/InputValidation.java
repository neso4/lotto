package lotto.validation;

import lotto.constant.LottoInformation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidation {

    private static final String ERROR = "[ERROR] ";
    private static final String IS_NULL_MESSAGE = "빈 문자열 입니다";
    private static final String IS_NOT_NUMBER_MESSAGE = "숫자가 아닙니다";
    private static final String IS_NOT_EXPRESS_NUMBER_RANGE_MESSAGE = "정수의 형태로 표현할 수 없습니다";
    private static final String IS_NOT_NUMBER_Range_MESSAGE = "0이하 입니다";
    private static final String IS_NOT_MONEY_UNIT_MESSAGE = LottoInformation.MONEY_UNIT.getNumber() + "원 단위가 아닙니다";
    private static final String IS_NOT_LOTTO_SIZE_MESSAGE = "번호가 6개가 아닙니다";
    private static final String IS_NOT_LOTTO_RANGE_MESSAGE = "1~45의 번호가 아닙니다";
    private static final String IS_DUPLICATION_LOTTO_NUMBER_MESSAGE = "중복된 숫자가 있습니다";

    public void validationMoney(String input) {
        isEmptyValidation(input);
        isNumberValidation(input);
        changeInteger(input);
        isNumberRangeValidation(input);
        isMoneyUnitValidation(input);
    }

    public void validationLottoWinningNumber(String lottoNumbers) {
        isEmptyValidation(lottoNumbers);
        validationSplitNumbers(lottoNumbers);
    }

    private void validationSplitNumbers(String lottoNumbers) {
        String[] lottoNumber = lottoNumbers.replace(" ", "").split(",");
        isLottoSize(lottoNumber);
        validationDetailNumbers(lottoNumber);
        isDuplicateNumber(lottoNumber);
    }

    private void validationDetailNumbers(String[] lottoNumber) {
        for (int i = 0; i < LottoInformation.LOTTO_NUMBER_TOTAL_SIZE.getNumber(); i++) {
            changeInteger(lottoNumber[i]);
            checkNumberRange(lottoNumber[i]);
        }
    }

    public void validationLottoWinningBonusNumber(String bonusNumber, List<Integer> lottoWinningNumbers) {
        isEmptyValidation(bonusNumber);
        changeInteger(bonusNumber);
        checkNumberRange(bonusNumber);
        checkWinningNumbersDuplication(lottoWinningNumbers, bonusNumber);
    }

    private void isEmptyValidation(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR + IS_NULL_MESSAGE);
        }
    }

    private void isNumberValidation(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new IllegalArgumentException(ERROR + IS_NOT_NUMBER_MESSAGE);
            }
        }
    }

    private void changeInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR + IS_NOT_EXPRESS_NUMBER_RANGE_MESSAGE);
        }
    }

    private void isNumberRangeValidation(String input) {
        if (Integer.parseInt(input) < LottoInformation.MIN_LOTTO_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ERROR + IS_NOT_NUMBER_Range_MESSAGE);
        }
    }

    private void isMoneyUnitValidation(String input) {
        if (Integer.parseInt(input) % LottoInformation.MONEY_UNIT.getNumber() != 0) {
            throw new IllegalArgumentException(ERROR + IS_NOT_MONEY_UNIT_MESSAGE);
        }
    }

    private void isLottoSize(String[] lottoNumber) {
        if (lottoNumber.length != LottoInformation.LOTTO_NUMBER_TOTAL_SIZE.getNumber()) {
            throw new IllegalArgumentException(ERROR + IS_NOT_LOTTO_SIZE_MESSAGE);
        }
    }

    private void checkNumberRange(String s) {
        if (Integer.parseInt(s) < LottoInformation.MIN_LOTTO_NUMBER.getNumber() ||
                Integer.parseInt(s) > LottoInformation.MAX_LOTTO_NUMBER.getNumber()) {
            throw new IllegalArgumentException(ERROR + IS_NOT_LOTTO_RANGE_MESSAGE);
        }
    }

    private void isDuplicateNumber(String[] lottoNumber) {
        Set<String> checkDuplicationNumber = new HashSet<>(List.of(lottoNumber));
        if (checkDuplicationNumber.size() != LottoInformation.LOTTO_NUMBER_TOTAL_SIZE.getNumber()) {
            throw new IllegalArgumentException(ERROR + IS_DUPLICATION_LOTTO_NUMBER_MESSAGE);
        }
    }

    private void checkWinningNumbersDuplication(List<Integer> lottoWinningNumbers, String bonusNumber) {
        if(lottoWinningNumbers.contains(Integer.parseInt(bonusNumber))){
            throw new IllegalArgumentException(ERROR + IS_DUPLICATION_LOTTO_NUMBER_MESSAGE);
        }
    }
}
