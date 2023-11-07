package lotto.util;

import lotto.exception.InvalidLottoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class LottoUtil {

    private final InvalidLottoException lottoException = new InvalidLottoException();

    public List<Integer> lottoNumbersParser(String inputLottoNumbers) {
        lottoException.validInputLottoNumbers(inputLottoNumbers);
        List<Integer> lottoNumbers = new ArrayList<>();
        Arrays.stream(inputLottoNumbers.split(",")).map(number -> lottoNumbers.add(Integer.parseInt(number)))
                .collect(Collectors.toList());
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    public int bonusNumberParser(String inputBonusNumber) {
        lottoException.validInputBonusNumber(inputBonusNumber);
        return Integer.parseInt(inputBonusNumber);
    }
}
