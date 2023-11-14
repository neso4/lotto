package lotto.util;

import lotto.message.ExceptionMessage;

import java.util.List;

import static lotto.constant.ConstantValue.END_INCLUSIVE;
import static lotto.constant.ConstantValue.START_INCLUSIVE;

public class WinningLottoValidator {

    public static void validateLotto(List<Integer> lottoNumbers) {
        validateLottoNumbersOutOfRange(lottoNumbers);
    }

    public static void validateLottoNumbersOutOfRange(List<Integer> lottoNumbers) {
        for (Integer lottoNumber : lottoNumbers) {
            validateLottoNumberOutOfRange(lottoNumber);
        }
    }

    public static void validateLottoNumberOutOfRange(Integer lottoNumber) {
        if (lottoNumber < START_INCLUSIVE || lottoNumber > END_INCLUSIVE) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_OUT_OF_RANGE);
        }
    }
}
