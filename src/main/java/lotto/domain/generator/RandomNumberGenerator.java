package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constants.LottoConsts;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumbers;

import java.util.List;

public class RandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public LottoNumbers generateLottoNumbers(final int count) {
        final List<Integer> numbers = generateRandomNumbers(count);
        final List<LottoNumber> lottoNumbers = convertToLottoNumbers(numbers);

        return new LottoNumbers(lottoNumbers);
    }

    private List<Integer> generateRandomNumbers(final int count) {
        return Randoms
                .pickUniqueNumbersInRange(LottoConsts.LOTTO_NUMBER_MIN.getValue(), LottoConsts.LOTTO_NUMBER_MAX.getValue(), count);
    }

    private List<LottoNumber> convertToLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
