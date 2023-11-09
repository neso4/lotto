package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.LOTTO_RANGE_IN_END_VALUE;
import static lotto.domain.LottoNumber.LOTTO_RANGE_IN_START_VALUE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    public static List<Lotto> issueRandomLottos(int lottoQuantity) {
        return IntStream.range(0, lottoQuantity)
                .mapToObj(i -> generateLottoFromUniqueNumbers())
                .collect(Collectors.toList());
    }

    private static Lotto generateLottoFromUniqueNumbers() {
        return new Lotto(generateRangeOfUniqueNumbers());
    }

    private static List<Integer> generateRangeOfUniqueNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_RANGE_IN_START_VALUE,
                LOTTO_RANGE_IN_END_VALUE,
                LOTTO_SIZE);
    }

    public Lotto inputToGenerateLotto(String input) {
        List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
