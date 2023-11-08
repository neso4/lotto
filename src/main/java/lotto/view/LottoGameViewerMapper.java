package lotto.view;

import static lotto.engine.LottoSystemConstant.LOTTO_NUMBER_SEPARATOR;

import java.util.Arrays;
import java.util.List;
import lotto.engine.LottoSystemConstant.Policy;

public class LottoGameViewerMapper {
    public Integer toInt(String value) {
        return Integer.parseInt(value);
    }

    public List<Integer> toLottoNumbers(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(LOTTO_NUMBER_SEPARATOR.value()))
                .map(Integer::parseInt)
                .toList();
    }

    public List<String> toList(Policy<String> lottoNumberSeparator, String winningNumbers) {
        return Arrays.stream(winningNumbers.split(LOTTO_NUMBER_SEPARATOR.value()))
                .toList();
    }
}
